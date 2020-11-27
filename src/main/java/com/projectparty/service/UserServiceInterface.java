package com.projectparty.service;

import com.projectparty.entities.User;
import java.util.List;

public interface UserServiceInterface {

    void save(User user);

    List<User> readAll();

    User read(int id);

    boolean update(User user, int id);

    boolean delete(int id);

}
