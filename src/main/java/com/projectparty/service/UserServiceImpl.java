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
@Transactional //todo remove
public class UserServiceImpl implements UserService {

    public static final Logger logger = Logger.getLogger(UserServiceImpl.class.getName());

    private static final int OIL_ID = 10;
    private static final int METALS_ID = 20;
    private static final long INITIAL_CASH = 100;
    private static final int INITIAL_ITEM_AMOUNT = 100;
    private static final int INITIAL_FROZEN_ITEM_AMOUNT = 0;

    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public boolean save(User user) {
        setUserData(user);
        logger.log(Level.SEVERE, user.toString());
        userDao.save(user);
        return true;
    }


    private void setUserData(User user) {
        user.setRole(RoleType.ROLE_USER);
        Map<Integer, Integer> map = Map.of(
                OIL_ID, INITIAL_ITEM_AMOUNT,
                METALS_ID, INITIAL_ITEM_AMOUNT
        );

        Map<Integer, Integer> frozenMap = Map.of(
                OIL_ID, INITIAL_FROZEN_ITEM_AMOUNT,
                METALS_ID, INITIAL_FROZEN_ITEM_AMOUNT
        );
        user.setCash(INITIAL_CASH);
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
