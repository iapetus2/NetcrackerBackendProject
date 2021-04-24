package com.projectparty.dao;

import com.projectparty.entities.Role;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.logging.Logger;

@Repository
//todo transactional
//todo delete
public class RoleDao {

    private final SessionFactory sessionFactory;

    Logger logger = Logger.getLogger(UserDao.class.getName());

    @Autowired
    public RoleDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    public Role findByRoleName(String name) {
        try {
            Session session = sessionFactory
                    .getCurrentSession();
            return session.get(Role.class, name);
        } catch (Exception e){
            logger.severe("Error: " + e.getMessage());
            throw new RuntimeException("Can not read from database",e);
        }
    }
}
