package com.projectparty.security.service;

import com.projectparty.dao.UserDao;
import com.projectparty.entities.User;
import com.projectparty.service.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserDao userDao;

    @Transactional
    public UserDetails loadUserByUsername(String username) {
        User user = userDao.findByUsername(username);

        return UserDetailsImpl.build(user);
    }
}