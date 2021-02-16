package com.projectparty.dao;

import com.projectparty.entities.User;
import com.projectparty.utils.HibernateSessionFactoryUtil;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import java.util.List;



@Component
public class UserDao {
    Logger logger = Logger.getLogger(UserDao.class.getName());

    public void save(User user) {
        try {
            Session session = HibernateSessionFactoryUtil
                    .getSessionFactory()
                    .openSession();
            Transaction transaction = session
                    .beginTransaction();
            session.save(user);
            transaction.commit();
            session.close();
        }catch (RuntimeException e){
            logger.error("User creating failure");
        }

    }


    public List<User> readAll() {
        return (List<User>)  HibernateSessionFactoryUtil
                .getSessionFactory()
                .openSession()
                .createQuery("FROM User", User.class)
                .list();
    }

    public User read(int id) {
        return HibernateSessionFactoryUtil
                .getSessionFactory()
                .openSession()
                .get(User.class, id);
    }

    public boolean update(User user, int id) {
        try{
            Session session = HibernateSessionFactoryUtil
                    .getSessionFactory()
                    .openSession();
            session.load(User.class, id);
            Transaction transaction = session
                    .beginTransaction();
            session.update(user);
            transaction.commit();
            session.close();
        }catch (RuntimeException e){
            logger.error("TradingItem updating failure");
        }

        return true;
    }

    public boolean delete(int id) {
        try{
            User proxyUser;
            Session session = HibernateSessionFactoryUtil
                    .getSessionFactory()
                    .openSession();
            proxyUser = session.load(User.class, id);
            Transaction transaction = session
                    .beginTransaction();
            session.delete(proxyUser);
            transaction.commit();
            session.close();
        }catch (RuntimeException e){
            logger.error("TradingItem deleting failure");
        }

        return true;
    }
}
