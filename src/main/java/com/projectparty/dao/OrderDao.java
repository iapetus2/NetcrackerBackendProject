package com.projectparty.dao;

import com.projectparty.entities.Order;
import com.projectparty.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class OrderDao {
    private static final Logger logger = Logger.getLogger(OrderDao.class.getName());

    public void save(Order Order) {
        try (var session = HibernateSessionFactoryUtil
                .getSessionFactory()
                .openSession()) {
            Transaction transaction = session
                    .beginTransaction();
            try {
                session.save(Order);
                transaction.commit();
            } catch (final Exception e) {
                transaction.rollback();
            }
        } catch (Exception e){
            logger.log(Level.SEVERE, "Exception: ", e);
        }

    }

    public List<Order> readAll() {
        try(var session = HibernateSessionFactoryUtil
                .getSessionFactory()
                .openSession()) {
            return session.createQuery("FROM Order", Order.class)
                    .getResultList();
        }
    }

    public List<Order> readAllItems(int id) {
        try(var session = HibernateSessionFactoryUtil
                .getSessionFactory()
                .openSession()) {
            return session.createQuery("FROM Order WHERE tradingItemId = :itemId", Order.class)
                    .setParameter("itemId",id)
                    .list();
        }
    }

    public Order read(int id) {
        return HibernateSessionFactoryUtil
                .getSessionFactory()
                .openSession()
                .get(Order.class, id);
    }

    public boolean update(Order Order, int id) {
        try{
            Session session = HibernateSessionFactoryUtil
                    .getSessionFactory()
                    .openSession();
            session.load(Order.class, id);
            Transaction transaction = session
                    .beginTransaction();
            session.update(Order);
            transaction.commit();
            session.close();
        }catch (Exception e){
            throw new RuntimeException("Update failure");
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
        } catch (Exception e) {
            throw new RuntimeException("Delete failure");
        }

        return true;
    }
}
