package com.projectparty.dao;

import com.projectparty.entities.User;
import com.projectparty.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import java.util.logging.Level;
import java.util.logging.Logger;


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
        }catch (Exception e){
            logger.log(Level.SEVERE, "Exception: ", e);
        }

    }

//todo make it clear how to do that

//    List<User> readAll() {
//        return (List<User>)  HibernateSessionFactoryUtil
//                .getSessionFactory()
//                .openSession()
//                .createQuery("FROM User")
//                .list();
//    }

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
        }catch (Exception e){
            throw new RuntimeException("Update failure");
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
        } catch (Exception e) {
            throw new RuntimeException("Delete failure");
        }

        return true;
    }
}
