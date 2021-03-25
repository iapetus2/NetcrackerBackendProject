package com.projectparty.service;

import com.projectparty.dao.UserDao;
import com.projectparty.entities.User;
import com.projectparty.entities.UserRoleEnum;
import com.projectparty.security.service.UserDetailsServiceImpl;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService{

    public static final Logger logger = Logger.getLogger(UserServiceImpl.class.getName());

    @Autowired
    UserDao userDao;

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    PasswordEncoder encoder;

    @Override
    public boolean save(User user) {

        user.setRole(UserRoleEnum.ROLE_USER);
        logger.log(Level.INFO, user.toString());
        userDao.save(user);
        return true;
    }

//    private void createNewUserCollections(UserDetails user){
//        user.set(new TreeMap<>());
//        user.setDeals(new ArrayList<>());
//        user.setOrders(new ArrayList<>());
//    }

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
