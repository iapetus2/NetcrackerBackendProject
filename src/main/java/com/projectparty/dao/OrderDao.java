package com.projectparty.dao;

import com.projectparty.entities.Order;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
@Transactional
public class OrderDao {
    private static final Logger logger = Logger.getLogger(OrderDao.class.getName());

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
            logger.log(Level.SEVERE, "Exception: ", e);
        }
    }

    public List<Order> readAll() {
        var session = sessionFactory
                .getCurrentSession();
        return session.createQuery("FROM Order", Order.class)
                .list();
    }

    public List<Order> readAllItems(int id) {
        var session = sessionFactory
                .getCurrentSession();
        return session.createQuery("FROM Order WHERE tradingItemId = :itemId", Order.class)
                .setParameter("itemId", id)
                .list();
    }

    public Order read(int id) {
        var session = sessionFactory
                .getCurrentSession();
        return session.get(Order.class, id);

    }

    public boolean update(Order order, int id) {
        try {
            var session = sessionFactory
                    .getCurrentSession();
            session.load(Order.class, id);
            session.update(order);
        } catch (Exception e) {
            throw new RuntimeException("Update failure");
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
            throw new RuntimeException("Delete failure");
        }

        return true;
    }
}
