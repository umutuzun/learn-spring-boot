package com.umut.learnspringboot.dao;

import com.umut.learnspringboot.model.User;

import java.util.List;
import java.util.UUID;

/**
 * Created by Umut Uzun on 1/30/2018.
 */
public interface UserDao {

    List<User> getAllUsers();

    User getUser(UUID userUid);

    int updateUser(User user);

    int removeUser(UUID userUid);

    int insertUser(UUID userUid, User user);
}
