package com.projectparty.service;

import com.projectparty.dao.UserDao;
import com.projectparty.dao.UserDao;
import com.projectparty.entities.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDao();

    @Override
    public void save(User user) {
        userDao.save(user);
    }

    @Override
    public List<User> readAll() {
        return userDao.readAll();
    }

    @Override
    public User read(int id) {
        return userDao.read(id);
    }

    @Override
    public boolean update(User user, int id) {
        return userDao.update(user, id);
    }

    @Override
    public boolean delete(int id) {
        return userDao.delete(id);
    }
}
