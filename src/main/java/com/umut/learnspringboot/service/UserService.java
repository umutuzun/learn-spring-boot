package com.umut.learnspringboot.service;

import com.umut.learnspringboot.dao.UserDao;
import com.umut.learnspringboot.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.NotFoundException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;

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

    public List<User> getAllUsers(Optional<String> gender) {
        List<User> users = userDao.selectAllUsers();
        if (!gender.isPresent()) {
            return users;
        }
        try {
            User.Gender queriedGender = User.Gender.valueOf(gender.get().toUpperCase());
            return users.stream()
                    .filter(user -> user.getGender().equals(queriedGender)).collect(Collectors.toList());
        } catch (Exception e) {
            throw new IllegalStateException("Invalid gender", e);
        }
    }

    public Optional<User> getUser(UUID userUid) {
        return userDao.selectUserByUuid(userUid);
    }

    public int updateUser(User user) {
        Optional<User> userOptional = getUser(user.getUserUid());
        if (userOptional.isPresent()){
           return userDao.updateUser(user);
        }
        throw new NotFoundException("user " + user.getUserUid() + " not found");
    }

    public int removeUser(UUID uid) {
        UUID userUid = getUser(uid)
                .map(User::getUserUid)
                .orElseThrow(() -> new NotFoundException("user " + uid + " not found"));
        return userDao.deleteUserByUuid(userUid);
    }

    public int insertUser(User user) {
        UUID userUid = user.getUserUid() == null ? UUID.randomUUID() : user.getUserUid();
        return userDao.insertUser(userUid, User.newUser(userUid, user));
    }

}

