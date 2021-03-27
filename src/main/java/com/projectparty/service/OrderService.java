package com.projectparty.service;

import com.projectparty.entities.Order;
import com.projectparty.messages.OrderMessage;

import java.util.List;

public interface OrderService {
    void save(Order order);

    List<Order> readAll();

    List<OrderMessage> readAllItemsById(int itemId);

    Order read(int id);

    boolean update(Order order, int id);

    boolean delete(int id);
}
