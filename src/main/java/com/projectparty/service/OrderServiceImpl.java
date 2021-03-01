package com.projectparty.service;

import com.projectparty.dao.OrderDao;
import com.projectparty.entities.Order;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao = new OrderDao();

    @Override
    public void save(Order order) {
        orderDao.save(order);
    }

    @Override
    public List<Order> readAll() {
        return orderDao.readAll();
    }

    @Override
    public Order read(int id) {
        return orderDao.read(id);
    }

    @Override
    public boolean update(Order order, int id) {
        return orderDao.update(order, id);
    }

    @Override
    public boolean delete(int id) {
        return orderDao.delete(id);
    }
}