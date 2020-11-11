package com.projectparty.dao;

import com.projectparty.entities.TradingItem;
import com.projectparty.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class TradingItemDao {

    public void save(TradingItem tradingItem) {
        try {
            Session session = HibernateSessionFactoryUtil
                    .getSessionFactory()
                    .openSession();
            Transaction tx1 = session
                    .beginTransaction();
            session.save(tradingItem);
            tx1.commit();
            session.close();
        }catch (Exception e){
            System.out.println("Create failure");
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
        try {
            Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            Transaction tx1 = session.beginTransaction();
            session.update(tradingItem);
            tx1.commit();
            session.close();
        }catch (Exception e){
            System.out.println("Update failure");

            return false;
        }
        System.out.println("Successfully updated");

        return true;
    }

    public boolean delete(int id) {
        try {
            TradingItem tradingItem;
            Session session = HibernateSessionFactoryUtil
                    .getSessionFactory()
                    .openSession();
            tradingItem = session.load(TradingItem.class, id);
            Transaction tx1 = session
                    .beginTransaction();
            session.delete(tradingItem);
            tx1.commit();
            session.close();
        }catch (Exception e){
            System.out.println("Delete failure");

            return false;
        }
        System.out.println("Successfully deleted");

        return true;
    }
}
