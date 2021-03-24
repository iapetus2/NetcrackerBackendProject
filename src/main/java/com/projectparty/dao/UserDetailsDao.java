package com.projectparty.dao;

import com.projectparty.entities.UserDetails;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


@Component
@EnableAutoConfiguration(exclude = HibernateJpaAutoConfiguration.class)
public class UserDetailsDao {
    Logger logger = Logger.getLogger(UserDetailsDao.class.getName());

    private final SessionFactory sessionFactory;

    public UserDetailsDao(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    public void save(UserDetails userDetails) {
        try {
            logger.log(Level.INFO, userDetails.toString());
            var session = sessionFactory
                    .getCurrentSession();
            session.save(userDetails);
        }catch (Exception e){
            logger.log(Level.SEVERE, "Exception: ", e);
        }

    }

    public UserDetails findByUsername(String name) {
        try {
            Session session = sessionFactory
                    .getCurrentSession();
            return session.get(UserDetails.class, name);
        } catch (Exception e){
            logger.severe("Error: " + e.getMessage());
            throw new RuntimeException("Can not read from database",e);
        }
    }


    public List<UserDetails> readAll() {
        try {
            var session = sessionFactory
                    .getCurrentSession();
            return session.createQuery("FROM UserDetails", UserDetails.class)
                    .list();
        }
        catch (Exception e){
            logger.severe("Error: " + e.getMessage());
            throw new RuntimeException("Can not read database");
        }
    }

    public UserDetails read(int id) {
        try {
            Session session = sessionFactory
                    .getCurrentSession();
            return session.get(UserDetails.class, id);
        } catch (Exception e){
            logger.severe("Error: " + e.getMessage());
            throw new RuntimeException("Can not read from database",e);
        }
    }

    public boolean update(UserDetails userDetails, int id) {
        try {
            var session = sessionFactory
                    .getCurrentSession();
            session.load(UserDetails.class, id);
            session.update(userDetails);
        } catch (Exception e){
            logger.severe("Error: " + e.getMessage());
            throw new RuntimeException("Update failure");
        }

        return true;
    }

    public boolean delete(int id) {
        try {
            UserDetails proxyUser;
            var session = sessionFactory
                    .getCurrentSession();
            proxyUser = session.load(UserDetails.class, id);
            session.delete(proxyUser);
        } catch (Exception e) {
            logger.severe("Error: " + e.getMessage());
            throw new RuntimeException("Delete failure");
        }
        return true;
    }
}

