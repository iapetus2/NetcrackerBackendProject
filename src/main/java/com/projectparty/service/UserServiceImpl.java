package com.projectparty.service;

import com.projectparty.dao.UserDao;
import com.projectparty.dao.RoleRepository;
import com.projectparty.entities.User;
import com.projectparty.entities.Role;
import com.projectparty.entities.UserRoleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    UserDao userDao;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserDetails loadUserByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public boolean save(User user) {
        User userFromDB = userDao.findByUsername(user.getUsername());

        if (userFromDB != null) {
            return false;
        }
        user.setRoles(Collections.singleton(new Role(1L, UserRoleEnum.ROLE_USER)));
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userDao.save(user);
        return true;
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
