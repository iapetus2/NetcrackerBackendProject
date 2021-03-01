package com.projectparty.dao;

import com.projectparty.entities.User;
import com.projectparty.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import java.util.List;
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
        
        try (Session session = HibernateSessionFactoryUtil
                .getSessionFactory()
                .openSession()) {
            Transaction transaction = session
                    .beginTransaction();
            try {
                session.load(User.class, id);
                session.update(user);
                transaction.commit();
            } catch (final Exception e) {
                logger.severe("Error: " + e.getMessage());
                transaction.rollback();
            }
        } catch (Exception e){
            throw new RuntimeException("Update failure");
        }

        return true;
    }

    public boolean update(User user, int id, Session session) {
        try{
                session.load(User.class, id);
                session.merge(user);
        }catch (Exception e){
            session.getTransaction().rollback();
            throw new RuntimeException("Update failure");
        }
        return true;
    }

    public Session openSession(){
        Session session;
        try {
            session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();
        } catch (Exception e){
            try {
                session = HibernateSessionFactoryUtil
                        .getSessionFactory()
                        .openSession();
            } catch (Exception ex) {
                throw new RuntimeException("Fail to open Session", ex);
            }
        }
        return session;
    }

    public void commitTransaction(Session session, Transaction transaction){
        try {
            transaction.commit();
            session.close();
        }catch (Exception e){
            session.getTransaction().rollback();
            throw new RuntimeException("Fail to commit changes");
        }
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
