package com.projectparty.service;

import com.projectparty.entities.Order;

import java.util.List;

public interface OrderService {
    void save(Order order);

    List<Order> readAll();

    List<Order> readAllItems(int itemId);

    Order read(int id);

    boolean update(Order order, int id);

    boolean delete(int id);
}
