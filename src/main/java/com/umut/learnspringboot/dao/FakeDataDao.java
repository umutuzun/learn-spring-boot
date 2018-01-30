package com.umut.learnspringboot.dao;

import com.umut.learnspringboot.model.User;
import com.umut.learnspringboot.model.User.Gender;

import java.util.*;

/**
 * Created by Umut Uzun on 1/30/2018.
 */
public class FakeDataDao implements UserDao {

    private static Map<UUID, User> database;

    static {
        database = new HashMap<>();
        UUID umutUserUid = UUID.randomUUID();
        database.put(umutUserUid, new User(umutUserUid, "Umut", "Uzun","umutuzun@example.com", Gender.MALE, 29 ));
    }

    @Override
    public List<User> selectAllUsers() {
        return new ArrayList<>(database.values());
    }

    @Override
    public User selectUserByUuid(UUID userUuid) {
        return database.get(userUuid);
    }

    @Override
    public int updateUser(User user) {
        database.put(user.getUserUid(), user);
        return 1;
    }

    @Override
    public int deleteUserByUuid(UUID userUid) {
        database.remove(userUid);
        return 1;
    }

    @Override
    public int insertUser(UUID userUid, User user) {
        database.put(userUid, user);
        return 1;
    }
}
