package com.projectparty.dao;

import com.projectparty.entities.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


@Component
public class UserDao {
    Logger logger = Logger.getLogger(UserDao.class.getName());

    private final SessionFactory sessionFactory;

    public UserDao(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    public void save(User user) {
        try {
            logger.log(Level.INFO, user.toString());
            var session = sessionFactory
                    .getCurrentSession();
            session.save(user);
        }catch (Exception e){
            logger.log(Level.SEVERE, "Exception: ", e);
        }

    }

    public User findByUsername(String name) {
        try {
            Session session = sessionFactory
                    .getCurrentSession();
            return session.get(User.class, name);
        } catch (Exception e){
            logger.severe("Error: " + e.getMessage());
            throw new RuntimeException("Can not read from database",e);
        }
    }


    public List<User> readAll() {
        try {
            var session = sessionFactory
                    .getCurrentSession();
            return session.createQuery("FROM User", User.class)
                    .list();
        }
        catch (Exception e){
            logger.severe("Error: " + e.getMessage());
            throw new RuntimeException("Can not read database");
        }
    }

    public User read(int id) {
        try {
            Session session = sessionFactory
                    .getCurrentSession();
            return session.get(User.class, id);
        } catch (Exception e){
            logger.severe("Error: " + e.getMessage());
            throw new RuntimeException("Can not read from database",e);
        }
    }

    public boolean update(User user, int id) {
        try {
            var session = sessionFactory
                    .getCurrentSession();
            session.load(User.class, id);
            session.update(user);
        } catch (Exception e){
            logger.severe("Error: " + e.getMessage());
            throw new RuntimeException("Update failure");
        }

        return true;
    }

    public boolean delete(int id) {
        try {
            User proxyUser;
            var session = sessionFactory
                    .getCurrentSession();
            proxyUser = session.load(User.class, id);
            session.delete(proxyUser);
        } catch (Exception e) {
            logger.severe("Error: " + e.getMessage());
            throw new RuntimeException("Delete failure");
        }
        return true;
    }
}
