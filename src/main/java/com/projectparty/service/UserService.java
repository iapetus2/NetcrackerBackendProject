package com.projectparty.service;

import com.projectparty.dao.UserDao;
import com.projectparty.entities.User;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserService implements UserServiceInterface {

    private UserDao itemsDao = new UserDao();

    @Override
    public void save(User user) {
        itemsDao.save(user);
    }

    @Override
    public List<User> readAll() {
        //TODO getting from db
        return null;
    }

    @Override
    public User read(int id) {
        return itemsDao.read(id);
    }

    @Override
    public boolean update(User user, int id) {
        return itemsDao.update(user, id);
    }

    @Override
    public boolean delete(int id) {
        return itemsDao.delete(id);
    }





}
