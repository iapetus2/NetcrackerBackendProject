package com.projectparty.service;

import com.projectparty.dao.UserDao;
import com.projectparty.entities.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserServiceInterface {

    private UserDao usersDao = new UserDao();

    @Override
    public void save(User user) {
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





}
