package com.umut.learnspringboot.service;

import com.umut.learnspringboot.model.User;

import java.util.List;
import java.util.UUID;

/**
 * Created by Umut Uzun on 1/30/2018.
 */
public class UserService {

    public List<User> getAllUsers() {
        return null;
    }


    public User getUser(UUID userUuid) {
        return null;
    }

    public int updateUser(User user) {
        return 1;
    }


    public int removeUser(UUID userUid) {
        return 1;
    }


    public int insertUser(UUID userUid, User user) {
        return 1;
    }
}

