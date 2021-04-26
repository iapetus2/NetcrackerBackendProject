package com.projectparty.service;

import com.projectparty.dao.DealDao;
import com.projectparty.dao.OrderDao;
import com.projectparty.entities.Deal;
import com.projectparty.entities.Order;
import com.projectparty.entities.OrderType;
import com.projectparty.entities.User;
import com.projectparty.exceptions.BusinessException;
import com.projectparty.messages.OrderMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private final OrderDao itemsDao;
    private final UserService userServiceImpl;
    private final DealService dealService;

    private static final Logger logger = Logger.getLogger(OrderService.class.getName());

    @Autowired
    public OrderServiceImpl(OrderDao itemsDao, UserService userServiceImpl, DealService dealService) {
        this.itemsDao = itemsDao;
        this.userServiceImpl = userServiceImpl;
        this.dealService = dealService;
    }

    @Override
    public void save(Order order) {
        order.setUser(userServiceImpl.read(order.getUser().getId()));

        validateOrder(order);
        List<Order> strategyOrders = findMatchingOrders(order);
        changeFrozenParameters(order);

        if (!strategyOrders.isEmpty()) {
            makeDeals(strategyOrders, order);
            if (order.getAmount() > 0) {
                itemsDao.save(order);
                logger.log(Level.INFO,
                        "New order has been saved to DB, deal_id = " + order.getId());
            }
        } else {
            itemsDao.save(order);
            logger.log(Level.INFO,
                    "New order has been saved to DB, deal_id = " + order.getId());
        }
    }

    @Override
    public List<Order> readAll() {
        logger.log(Level.INFO,"Getting all orders from database");
        return itemsDao.readAll();
    }

    @Override
    public List<OrderMessage> readAllItemsById(int itemId) {
        List<Order> orderList = itemsDao.readAllItemsById(itemId);
        List<OrderMessage> messageList = new ArrayList<>();
        orderList.forEach(order -> {
                    OrderMessage orderMessage = new OrderMessage(order);

                    if (!messageList.isEmpty() && messageList.contains(orderMessage)) {
                        int index = messageList.indexOf(orderMessage);
                        OrderMessage temporaryOrderMessage = messageList.get(index);
                        int amount = temporaryOrderMessage.getAmount();

                        temporaryOrderMessage.setAmount(order.getAmount() + amount);
                        messageList.set(index, temporaryOrderMessage);
                    } else {
                        messageList.add(orderMessage);
                    }
                }
        );
        return messageList;
    }

    @Override
    public Order read(int id) {
        logger.log(Level.INFO,"Getting all orders from database");
        return itemsDao.read(id);
    }

    @Override
    public boolean update(Order order, int id) {
        logger.log(Level.INFO,"Updating properties of an order with id=" + id);
        return itemsDao.update(order, id);
    }

    private void changeFrozenParameters(Order order) {
        User user = order.getUser();

        if (order.getType() == OrderType.BUY) {
            user.setFrozenCash(user.getFrozenCash() + order.getAmount() * order.getPrice());
        } else {
            int newFrozenAmount = user.getFrozenItems()
                    .get(order.getTradingItem().getId()) + order.getAmount();
            user.getFrozenItems()
                    .replace(order.getTradingItem().getId(), newFrozenAmount);
        }
    }

    private List<Order> findMatchingOrders(Order order) {
        final int tradingItemId = order.getTradingItem().getId();

        List<Order> orderList =
                filterOrderListByType(itemsDao.readAllItemsById(tradingItemId),
                        order.getType() == OrderType.BUY ? OrderType.SELL : OrderType.BUY, order);

        if (!checkOrderFeasibility(orderList, order)) {
            return Collections.emptyList();
        }

        order.getUser().getItems().putIfAbsent(tradingItemId, 0);

        List<Order> strategyOrders = new ArrayList<>();
        int amount = order.getAmount();

        for (Order runningOrder : orderList) {
            if (!checkForMatchingEnd(runningOrder, order) || amount <= 0) {
                break;
            }
            strategyOrders.add(runningOrder);
            amount = amount - runningOrder.getAmount();
        }

        return strategyOrders;
    }

    private void validateOrder(Order order) {
        if (order.getPrice() <= 0 || order.getAmount() <= 0) {
            logger.severe("Both price and amount must be positive");
            throw new RuntimeException("Both price and amount must be positive");
        }

        final User user = order.getUser();
        final int tradingItemId = order.getTradingItem().getId();
        final int amountUserOwns = user.getItems().getOrDefault(tradingItemId, 0);
        final int frozenAmountUserOwns = user.getFrozenItems().getOrDefault(tradingItemId, 0);

        if (order.getType() == OrderType.SELL
                && amountUserOwns - frozenAmountUserOwns < order.getAmount()) {
            logger.severe("Client doesn't have enough items to trade");
            throw new RuntimeException("Client doesn't have enough items to trade");
        }

        if (order.getType() == OrderType.BUY
                && user.getCash() - user.getFrozenCash() < order.getPrice() * order.getAmount()) {
            logger.severe("Insufficient funds");
            throw new RuntimeException("Insufficient funds");
        }
    }

    private boolean checkOrderFeasibility(List<Order> orderList, Order order) {
        if (orderList.isEmpty()) {
            return false;
        } else {
            if (order.getType() == OrderType.BUY
                    && order.getPrice() < orderList.get(0).getPrice()) {
                return false;
            }
            return order.getType() != OrderType.SELL
                    || order.getPrice() <= orderList.get(0).getPrice();
        }
    }

    private boolean checkForMatchingEnd(Order runningOrder, Order order) {
        if (order.getType() == OrderType.BUY && runningOrder.getPrice() > order.getPrice()) {
            return false;
        }

        return order.getType() != OrderType.SELL || runningOrder.getPrice() >= order.getPrice();
    }

    private void makeDeals(List<Order> strategyOrders, Order order) {
        User partner;
        User user = order.getUser();
        long orderPrice;

        for (Order currentOrder : strategyOrders) {
            orderPrice = currentOrder.getPrice();
            partner = userServiceImpl.read(currentOrder.getUser().getId());
            order.setAmount(Math.max(order.getAmount() - currentOrder.getAmount(), 0));

            Deal deal = new Deal();
            this.setNewDealParameters(deal, currentOrder, orderPrice);

            setCustomerOrSeller(user, order, orderPrice, currentOrder.getAmount(), deal);
            setCustomerOrSeller(partner, currentOrder, orderPrice, currentOrder.getAmount(), deal);

            userServiceImpl.deal(user, partner);
            dealService.save(deal);

            if (order.getAmount() >= 0) {
                itemsDao.delete(currentOrder.getId());
            }
        }
    }

    private void setCustomerOrSeller(User user, Order order, long orderPrice, int amount, Deal deal) {

        final int tradingItemId = order.getTradingItem().getId();

        if (order.getType() == OrderType.BUY) {
            user.setFrozenCash(user.getFrozenCash() - orderPrice);
            user.setCash(user.getCash() - orderPrice * amount);
            user.getItems()
                    .replace(tradingItemId, user.getItems().get(tradingItemId) + amount);
        } else {
            user.setCash(user.getCash() + orderPrice * amount);
            user.getFrozenItems()
                    .replace(tradingItemId, user.getFrozenItems().get(tradingItemId) - amount);
            user.getItems()
                    .replace(tradingItemId, user.getItems().get(tradingItemId) - amount);
        }
        user.getDeals().add(deal);
    }

    private void setNewDealParameters(Deal deal, Order order, long orderPrice) {
        deal.setDate(new Date());
        deal.setPrice(orderPrice);
        deal.setAmount(order.getAmount());
        deal.setDealItemId(order.getTradingItem().getId());
    }

    private List<Order> filterOrderListByType(List<Order> orderList, OrderType orderType, Order newOrder) {
        Comparator<Order> orderComparator = Comparator.comparing(Order::getPrice);

        if (orderType == OrderType.BUY) {
            orderComparator = orderComparator.reversed();
        }

        return orderList.stream()
                .filter(order -> (order.getType() == orderType) && (order.getUser().getId()
                        != newOrder.getUser().getId()))
                .sorted(orderComparator)
                .collect(Collectors.toList());
    }

    @Override
    public boolean delete(int id) {
        logger.log(Level.INFO,"Deleting order with id = " + id);
        return itemsDao.delete(id);
    }

}
