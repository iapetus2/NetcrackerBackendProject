package com.projectparty.dao;

import com.projectparty.entities.TradingItem;
import com.projectparty.utils.HibernateSessionFactoryUtil;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.logging.Level;


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
        }catch (RuntimeException e){
            logger.error("TradingItem creating failure");
        }

    }

    public List<TradingItem> readAll() {
        return HibernateSessionFactoryUtil
                .getSessionFactory()
                .openSession()
                .createQuery("FROM TradingItem", TradingItem.class)
                .list();
    }

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
        }catch (RuntimeException e){
            logger.error("TradingItem updating failure");
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
        }catch (RuntimeException e){
            logger.error("TradingItem deleting failure");
        }

        return true;
    }
}
