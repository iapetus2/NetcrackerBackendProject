package com.projectparty.service;

import com.projectparty.dao.OrderDao;
import com.projectparty.entities.Order;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderDao itemsDao = new OrderDao();

    @Override
    public void save(Order order) {
        itemsDao.save(order);
    }

    @Override
    public List<Order> readAll() {
        return itemsDao.readAll();
    }

    @Override
    public Order read(int id) {
        return itemsDao.read(id);
    }

    @Override
    public boolean update(Order order, int id) {
        return itemsDao.update(order, id);
    }

    @Override
    public boolean delete(int id) {
        return itemsDao.delete(id);
    }





}
