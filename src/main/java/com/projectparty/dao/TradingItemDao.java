package com.projectparty.dao;

import com.projectparty.entities.TradingItem;
import com.projectparty.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import java.util.logging.Level;
import java.util.logging.Logger;


@Component
public class TradingItemDao {
    Logger logger = Logger.getLogger(TradingItemDao.class.getName());

    public void save(TradingItem tradingItem) {
        try {
            Session session = HibernateSessionFactoryUtil
                    .getSessionFactory()
                    .openSession();
            Transaction transaction = session
                    .beginTransaction();
            session.save(tradingItem);
            transaction.commit();
            session.close();
        }catch (Exception e){
            logger.log(Level.SEVERE, "Exception: ", e);
        }

    }

//todo make it clear how to do that

//    List<TradingItem> readAll() {
//        return (List<TradingItem>)  HibernateSessionFactoryUtil
//                .getSessionFactory()
//                .openSession()
//                .createQuery("FROM TradingItem")
//                .list();
//    }

    public TradingItem read(int id) {
        return HibernateSessionFactoryUtil
                .getSessionFactory()
                .openSession()
                .get(TradingItem.class, id);
    }

    public boolean update(TradingItem tradingItem, int id) {
        try{
        Session session = HibernateSessionFactoryUtil
                .getSessionFactory()
                .openSession();
        session.load(TradingItem.class, id);
        Transaction transaction = session
                .beginTransaction();
        session.update(tradingItem);
        transaction.commit();
        session.close();
        }catch (Exception e){
            throw new RuntimeException("Update failure");
        }

        return true;
    }

    public boolean delete(int id) {
        try{
        TradingItem proxyTradingItem;
        Session session = HibernateSessionFactoryUtil
                .getSessionFactory()
                .openSession();
        proxyTradingItem = session.load(TradingItem.class, id);
        Transaction transaction = session
                .beginTransaction();
        session.delete(proxyTradingItem);
        transaction.commit();
        session.close();
        } catch (Exception e) {
            throw new RuntimeException("Delete failure");
        }

        return true;
    }
}
