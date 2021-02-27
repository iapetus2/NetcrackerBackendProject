package com.projectparty.service;

import com.projectparty.dao.UserDao;
import com.projectparty.entities.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

@Service
public class UserService implements UserServiceInterface {

    private final UserDao usersDao;

    @Autowired
    public UserService(UserDao usersDao) {
        this.usersDao = usersDao;
    }

    @Override
    public void save(User user) {
        user.setItems(new TreeMap<>());
        user.setDeals(new ArrayList<>());
        user.setOrders(new ArrayList<>());
        usersDao.save(user);
    }

    @Override
    public List<User> readAll() {
        return usersDao.readAll();
    }

    @Override
    public User read(int id) {
        return usersDao.read(id);
    }

    @Override
    public boolean update(User user, int id) {
        return usersDao.update(user, id);
    }

    @Override
    public boolean delete(int id) {
        return usersDao.delete(id);
    }

    @Override
    //add frozen cash functional
    public boolean deal(User customer, User seller) {
        Session session = usersDao.openSession();
        Transaction transaction = session.beginTransaction();

        usersDao.update(customer, customer.getUserId(),session);
        usersDao.update(seller, seller.getUserId(),session);

        usersDao.commitTransaction(session, transaction);

        return true;
    }
}
