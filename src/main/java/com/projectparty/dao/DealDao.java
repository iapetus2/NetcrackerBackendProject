package com.projectparty.dao;

import com.projectparty.entities.Deal;
import com.projectparty.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import java.util.List;
import org.apache.log4j.Logger;


@Component
public class DealDao {
    private static final Logger logger = Logger.getLogger(DealDao.class.getName());

    public void save(Deal deal) {
        try {
            Session session = HibernateSessionFactoryUtil
                    .getSessionFactory()
                    .openSession();
            Transaction transaction = session
                    .beginTransaction();
            session.save(deal);
            transaction.commit();
            session.close();
        }catch (RuntimeException e){
            logger.error("Deal creating failure");
        }

    }

    public List<Deal> readAll() {
        return HibernateSessionFactoryUtil
                .getSessionFactory()
                .openSession()
                .createQuery("FROM Deal", Deal.class)
                .list();
    }

    public Deal read(int id) {
        return HibernateSessionFactoryUtil
                .getSessionFactory()
                .openSession()
                .get(Deal.class, id);
    }

    public boolean update(Deal deal, int id) {
        try{
            Session session = HibernateSessionFactoryUtil
                    .getSessionFactory()
                    .openSession();
            session.load(Deal.class, id);
            Transaction transaction = session
                    .beginTransaction();
            session.update(deal);
            transaction.commit();
            session.close();
        }catch (RuntimeException e){
            logger.error("Deal updating failure");
        }

        return true;
    }

    public boolean delete(int id) {
        try{
            Deal proxyDeal;
            Session session = HibernateSessionFactoryUtil
                    .getSessionFactory()
                    .openSession();
            proxyDeal = session.load(Deal.class, id);
            Transaction transaction = session
                    .beginTransaction();
            session.delete(proxyDeal);
            transaction.commit();
            session.close();
        }catch (RuntimeException e){
            logger.error("Deal deleting failure");
        }

        return true;
    }
}
