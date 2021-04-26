package com.projectparty.dao;

import com.projectparty.entities.User;
import com.projectparty.exceptions.BusinessException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.stereotype.Component;

import javax.persistence.Query;
import java.util.List;

@Component
@EnableAutoConfiguration(exclude = HibernateJpaAutoConfiguration.class)
public class UserDao {

    private final SessionFactory sessionFactory;

    public UserDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void save(User user) {
        try {
            var session = sessionFactory
                    .getCurrentSession();
            session.save(user);
        } catch (Exception e) {
            throw new BusinessException("Error while saving new user to database ", e);
        }
    }

    public User findByUsername(String name) {
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session
                    .createQuery("SELECT id FROM User WHERE username = :name")
                    .setParameter("name", name);

            int id = (int) query.getSingleResult();

            return this.read(id);
        } catch (Exception e) {
            throw new BusinessException("Error while reading user's information from database ", e);
        }

    }

    public List<User> readAll() {
        try {
            var session = sessionFactory
                    .getCurrentSession();
            return session.createQuery("FROM User", User.class)
                    .list();
        } catch (Exception e) {
            throw new BusinessException("Error while reading all users from database ", e);
        }
    }

    public User read(int id) {
        try {
            Session session = sessionFactory
                    .getCurrentSession();
            return session.get(User.class, id);
        } catch (Exception e) {
            throw new BusinessException("Error while getting user's information from database ", e);
        }
    }

    public boolean update(User user, int id) {
        try {
            var session = sessionFactory
                    .getCurrentSession();
            session.load(User.class, id);
            session.update(user);
        } catch (Exception e) {
            throw new BusinessException("Error while updating user's information ", e);
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
            throw new BusinessException("Error while deleting a user from database ", e);
        }
        return true;
    }
}
