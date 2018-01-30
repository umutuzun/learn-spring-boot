package com.umut.learnspringboot.service;

import com.umut.learnspringboot.dao.UserDao;
import com.umut.learnspringboot.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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


    public Optional<User> getUser(UUID userUid) {
        return userDao.selectUserByUuid(userUid);
    }

    public int updateUser(User user) {
        Optional<User> userOptional = getUser(user.getUserUid());
        if (userOptional.isPresent()){
           return userDao.updateUser(user);
        }
        return -1;
    }


    public int removeUser(UUID userUid) {
        Optional<User> userOptional = getUser(userUid);
        if (userOptional.isPresent()) {
           return userDao.deleteUserByUuid(userUid);
        }
        return -1;
    }


    public int insertUser(User user) {
        UUID userUid = UUID.randomUUID();
        user.setUserUid(userUid);
        return userDao.insertUser(userUid, user);
    }
}

