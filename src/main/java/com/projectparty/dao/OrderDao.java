package com.projectparty.dao;

import com.projectparty.entities.Order;
import com.projectparty.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import java.util.List;
import org.apache.log4j.Logger;


@Component
public class OrderDao {
    private static final Logger logger = Logger.getLogger(OrderDao.class.getName());

    public void save(Order order) {
        try {
            Session session = HibernateSessionFactoryUtil
                    .getSessionFactory()
                    .openSession();
            Transaction transaction = session
                    .beginTransaction();
            session.save(order);
            transaction.commit();
            session.close();
        }catch (RuntimeException e){
            logger.error("Order creating failure");
        }

    }

    public List<Order> readAll() {
        return HibernateSessionFactoryUtil
                .getSessionFactory()
                .openSession()
                .createQuery("FROM Order", Order.class)
                .list();
    }

    public Order read(int id) {
        return HibernateSessionFactoryUtil
                .getSessionFactory()
                .openSession()
                .get(Order.class, id);
    }

    public boolean update(Order order, int id) {
        try{
            Session session = HibernateSessionFactoryUtil
                    .getSessionFactory()
                    .openSession();
            session.load(Order.class, id);
            Transaction transaction = session
                    .beginTransaction();
            session.update(order);
            transaction.commit();
            session.close();
        }catch (RuntimeException e){
            logger.error("Order updating failure");
        }

        return true;
    }

    public boolean delete(int id) {
        try{
            Order proxyOrder;
            Session session = HibernateSessionFactoryUtil
                    .getSessionFactory()
                    .openSession();
            proxyOrder = session.load(Order.class, id);
            Transaction transaction = session
                    .beginTransaction();
            session.delete(proxyOrder);
            transaction.commit();
            session.close();
        }catch (RuntimeException e){
            logger.error("Order deleting failure");
        }

        return true;
    }
}