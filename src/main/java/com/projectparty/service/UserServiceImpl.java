package com.projectparty.service;

import com.projectparty.dao.UserDao;
import com.projectparty.entities.Role;
import com.projectparty.entities.User;
import com.projectparty.entities.UserRoleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;

@Service
@Transactional
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

        createNewUserCollections(user);

        if (userFromDB != null) {
            return false;
        }
        user.setRoles(Collections.singleton(new Role(1L, UserRoleEnum.ROLE_USER)));
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userDao.save(user);
        return true;
    }

    private void createNewUserCollections(User user){
        user.setItems(new TreeMap<>());
        user.setDeals(new ArrayList<>());
        user.setOrders(new ArrayList<>());
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

    @Override
    //add frozen cash functional
    public boolean deal(User customer, User seller) {
        return userDao.update(customer, customer.getUserId()) &&
                userDao.update(seller, seller.getUserId());
    }
}
