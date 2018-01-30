package com.umut.learnspringboot.service;

import com.umut.learnspringboot.dao.UserDao;
import com.umut.learnspringboot.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Created by Umut Uzun on 1/30/2018.
 */
@Service
public class UserService {

    private UserDao userDao;

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public List<User> getAllUsers() {
        return userDao.selectAllUsers();
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

