package com.projectparty.service;

import com.projectparty.dao.OrderDao;
import com.projectparty.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private final OrderDao itemsDao;
  //  private final UserService userServiceImpl;
    //private final DealService dealService;

    @Autowired
    public OrderServiceImpl(OrderDao itemsDao, UserService userServiceImpl, DealService dealService) {
        this.itemsDao = itemsDao;
      //  this.userServiceImpl = userServiceImpl;
       // this.dealService = dealService;
    }

//    @Override
//    public void save(Order order) {
//        order.setUser(userServiceImpl.read(order.getUser().getUserId()));
//
//        validateOrder(order);
//        List<Order> strategyOrders = findMatchingOrders(order);
//
//        if (!strategyOrders.isEmpty()) {
//            makeDeals(strategyOrders, order);
//            if(order.getAmount() > 0){
//                saveTransaction(order);
//            }
//        }
//        else{
//            saveTransaction(order);
//        }
//    }

    public void saveTransaction(Order order){
        itemsDao.save(order);
    }

    @Override
    public void save(Order order) {

    }

    @Override
    public List<Order> readAll() {
        return itemsDao.readAll();
    }

    @Override
    public List<Order> readAllItems(int itemId) {
        return itemsDao.readAllItems(itemId);
    }

    @Override
    public Order read(int id) {
        return itemsDao.read(id);
    }

    @Override
    public boolean update(Order order, int id) {
        return itemsDao.update(order, id);
    }

//    private List<Order> findMatchingOrders(Order order) {
//        final int tradingItemId = order.getTradingItem().getItemId();
//
//        List<Order> orderList =
//                filterOrderListByType(itemsDao.readAllItems(tradingItemId),
//                        order.getOrderType() == OrderType.BUY ? OrderType.SELL : OrderType.BUY, order);
//
//        if(!checkDealFeasibility(orderList, order)){
//            return Collections.emptyList();
//        }
//
//        order.getUser().getItems().putIfAbsent(tradingItemId, 0);
//
//        List<Order> strategyOrders = new ArrayList<>();
//        int amount = order.getAmount();
//
//        for(Order runningOrder : orderList){
//            if(!checkForMatchingEnd(runningOrder, order) || amount <= 0){
//                break;
//            }
//            strategyOrders.add(runningOrder);
//            amount = amount - runningOrder.getAmount();
//        }
//
//        return strategyOrders;
//    }

//    private void validateOrder(Order order){
//        if (order.getOrderPrice() <= 0 || order.getAmount() <= 0) {
//            throw new RuntimeException("Both price and amount must be positive");
//        }
//
//        final int tradingItemId = order.getTradingItem().getItemId();
//        final int amountUserOwns = order.getUser().getItems().getOrDefault(tradingItemId, 0);
//
//        if (order.getOrderType() == OrderType.SELL && amountUserOwns < order.getAmount()) {
//            throw new RuntimeException("Client doesn't have enough items to trade");
//        }
//
//        if (order.getUser().getCash() < order.getOrderPrice() * order.getAmount()) {
//            throw new RuntimeException("Insufficient funds");
//        }
//    }

    private boolean checkDealFeasibility(List<Order> orderList, Order order){
        if(orderList.isEmpty()){
            return false;
        }
        else{
            if(order.getOrderType() == OrderType.BUY
                    && order.getOrderPrice() < orderList.get(0).getOrderPrice()){
                return false;
            }
            return order.getOrderType() != OrderType.SELL
                    || order.getOrderPrice() <= orderList.get(0).getOrderPrice();
        }
    }

    private boolean checkForMatchingEnd(Order runningOrder, Order order){
        if(order.getOrderType() == OrderType.BUY && runningOrder.getOrderPrice() > order.getOrderPrice()){
            return false;
        }

        return order.getOrderType() != OrderType.SELL || runningOrder.getOrderPrice() >= order.getOrderPrice();
    }

    //this method needs to be supplemented
//    private void makeDeals(List<Order> strategyOrders, Order order){
//        UserDetails partner;
//        UserDetails user = order.getUser();
//        long orderPrice;
//
//        for(Order currentOrder : strategyOrders){
//            orderPrice = currentOrder.getOrderPrice();
//            partner = userServiceImpl.read(currentOrder.getUser().getUserId());
//            order.setAmount(Math.max(order.getAmount() - currentOrder.getAmount(), 0));
//
//            Deal deal = new Deal();
//            this.setNewDealParameters(deal, currentOrder, orderPrice);
//
//            setCustomerOrSeller(user, order, orderPrice, currentOrder.getAmount(), deal);
//            setCustomerOrSeller(partner,currentOrder, orderPrice,currentOrder.getAmount(),deal);
//
//            userServiceImpl.deal(user, partner);
//            dealService.save(deal);
//
//            if(order.getAmount() >= 0){
//                itemsDao.delete(currentOrder.getOrderId());
//            }
//        }
//    }
//
//    private void setCustomerOrSeller(User user, Order order, long orderPrice, int amount, Deal deal) {
//
//        final int tradingItemId = order.getTradingItem().getItemId();
//
//        if(order.getOrderType() == OrderType.BUY) {
//            user.setCash(user.getCash() - orderPrice * amount);
//            user.getItems()
//                    .replace(tradingItemId, user.getItems().get(tradingItemId) + amount);
//        }
//        else{
//            user.setCash(user.getCash() + orderPrice * amount);
//            user.getItems()
//                    .replace(tradingItemId, user.getItems().get(tradingItemId) - amount);
//        }
//        user.getDeals().add(deal);
//    }
//
//    private void setNewDealParameters(Deal deal, Order order, long orderPrice){
//        deal.setDealDate(new Date());
//        deal.setUser(order.getUser());
//        deal.setDealPrice(orderPrice);
//        deal.setAmount(order.getAmount());
//    }

//    private List<Order> filterOrderListByType(List<Order> orderList, OrderType orderType,Order newOrder) {
//        Comparator<Order> orderComparator = Comparator.comparing(Order::getOrderPrice);
//
//        if (orderType == OrderType.BUY) {
//            orderComparator = orderComparator.reversed();
//        }
//
//        return orderList.stream()
//                .filter(order -> (order.getOrderType() == orderType) && (order.getUser().getUserId() != newOrder.getUser().getUserId()))
//                .sorted(orderComparator)
//                .collect(Collectors.toList());
//    }

    @Override
    public boolean delete(int id) {
        return itemsDao.delete(id);
    }

}
