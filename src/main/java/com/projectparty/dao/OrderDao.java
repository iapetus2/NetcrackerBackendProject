package com.projectparty.dao;

import com.projectparty.entities.Order;
import com.projectparty.exceptions.BusinessException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public OrderDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void save(Order order) {
        try {
            Session session = sessionFactory
                    .getCurrentSession();
            session.save(order);
        } catch (Exception e) {
            throw new BusinessException("Error while saving new order to database ", e);
        }
    }

    public List<Order> readAll() {
        try {
            var session = sessionFactory
                    .getCurrentSession();
            return session.createQuery("FROM Order", Order.class)
                    .list();
        } catch (Exception e) {
            throw new BusinessException("Error while reading all orders from database ", e);
        }
    }

    public List<Order> readAllItemsById(int id) {
        try {
            var session = sessionFactory
                    .getCurrentSession();
            return session.createQuery("FROM Order WHERE tradingItemId = :itemId", Order.class)
                    .setParameter("itemId", id)
                    .list();
        } catch (Exception e) {
            throw new BusinessException("Error while reading all orders from database, itemId = " + id, e);
        }
    }

    public Order read(int id) {
        try {
            var session = sessionFactory
                    .getCurrentSession();
            return session.get(Order.class, id);
        } catch (Exception e) {
            throw new BusinessException("Error while getting order from database ", e);
        }
    }

    public boolean update(Order order, int id) {
        try {
            var session = sessionFactory
                    .getCurrentSession();
            session.load(Order.class, id);
            session.update(order);
        } catch (Exception e) {
            throw new BusinessException("Error while updating an order ", e);
        }

        return true;
    }

    public boolean delete(int id) {
        try {
            Order proxyOrder;
            var session = sessionFactory
                    .getCurrentSession();
            proxyOrder = session.load(Order.class, id);
            session.delete(proxyOrder);
        } catch (Exception e) {
            throw new BusinessException("Error while deleting an order from database ", e);
        }

        return true;
    }
}
