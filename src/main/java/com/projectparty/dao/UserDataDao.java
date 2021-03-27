package com.projectparty.dao;

import com.projectparty.entities.UserData;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;


@Component
@EnableAutoConfiguration(exclude = HibernateJpaAutoConfiguration.class)
public class UserDataDao {
    Logger logger = Logger.getLogger(UserDataDao.class.getName());

    private final SessionFactory sessionFactory;

    public UserDataDao(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    public void save(UserData userData) {
        try {
            logger.log(Level.INFO, userData.toString());
            var session = sessionFactory
                    .getCurrentSession();
            createNewUserCollections(userData);
            session.save(userData);
        }catch (Exception e){
            logger.log(Level.SEVERE, "Exception: ", e);
        }

    }

    private void createNewUserCollections(UserData userData){
        userData.setItems(new TreeMap<>());
        userData.setDeals(new ArrayList<>());
        userData.setOrders(new ArrayList<>());
    }

    public UserData findByUsername(String name) {
        try {
            Session session = sessionFactory
                    .getCurrentSession();
            return session.get(UserData.class, name);
        } catch (Exception e){
            logger.severe("Error: " + e.getMessage());
            throw new RuntimeException("Can not read from database",e);
        }
    }


    public List<UserData> readAll() {
        try {
            var session = sessionFactory
                    .getCurrentSession();
            return session.createQuery("FROM UserData", UserData.class)
                    .list();
        }
        catch (Exception e){
            logger.severe("Error: " + e.getMessage());
            throw new RuntimeException("Can not read database",e);
        }
    }

    public UserData read(int id) {
        try {
            Session session = sessionFactory
                    .getCurrentSession();
            return session.get(UserData.class, id);
        } catch (Exception e){
            logger.severe("Error: " + e.getMessage());
            throw new RuntimeException("Can not read from database",e);
        }
    }

    public boolean update(UserData userData, int id) {
        try {
            var session = sessionFactory
                    .getCurrentSession();
            session.load(UserData.class, id);
            session.update(userData);
        } catch (Exception e){
            logger.severe("Error: " + e.getMessage());
            throw new RuntimeException("Update failure");
        }

        return true;
    }

    public boolean delete(int id) {
        try {
            UserData proxyUser;
            var session = sessionFactory
                    .getCurrentSession();
            proxyUser = session.load(UserData.class, id);
            session.delete(proxyUser);
        } catch (Exception e) {
            logger.severe("Error: " + e.getMessage());
            throw new RuntimeException("Delete failure");
        }
        return true;
    }
}

