package com.projectparty.service;

import com.projectparty.dao.UserDao;
import com.projectparty.entities.RoleType;
import com.projectparty.entities.User;
import com.projectparty.security.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    public static final Logger logger = Logger.getLogger(UserServiceImpl.class.getName());

    UserDao userDao;

    UserDetailsServiceImpl userDetailsService;

    PasswordEncoder encoder;

    @Autowired
    public UserServiceImpl(UserDao userDao, UserDetailsServiceImpl userDetailsService, PasswordEncoder encoder) {
        this.userDao = userDao;
        this.userDetailsService = userDetailsService;
        this.encoder = encoder;
    }

    @Override
    public boolean save(User user) {
        setUserData(user);
        logger.log(Level.SEVERE, user.toString());
        userDao.save(user);
        return true;
    }


    private void setUserData(User user){
        user.setRole(RoleType.ROLE_USER);
        Map<Integer,Integer> map = Map.of(
                10,100,
                20,100
        );
        
        Map<Integer,Integer> frozenMap = Map.of(
                10,0,
                20,0
        );
        user.setCash(100);
        user.setItems(map);
        user.setFrozenItems(frozenMap);
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
    public boolean deal(User customer, User seller) {
        return userDao.update(customer, customer.getUserId()) &&
                userDao.update(seller, seller.getUserId());
    }
}
