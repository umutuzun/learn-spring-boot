package com.umut.learnspringboot.dao;

import com.umut.learnspringboot.model.User;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * Created by Umut Uzun on 1/30/2018.
 */
public class FakeDataDaoTest {

    private FakeDataDao fakeDataDao;

    @Before
    public void setUp() throws Exception {
        fakeDataDao = new FakeDataDao();
    }

    @Test
    public void shouldSelectAllUsers() throws Exception {
        List<User> users = fakeDataDao.selectAllUsers();
        assertThat(users).hasSize(1);

        User user = users.get(0);

        assertThat(user.getAge()).isEqualTo(29);
        assertThat(user.getFirstName()).isEqualTo("Umut");
        assertThat(user.getLastName()).isEqualTo("Uzun");
        assertThat(user.getGender()).isEqualTo(User.Gender.MALE);
        assertThat(user.getEmail()).isEqualTo("umutuzun@example.com");
        assertThat(user.getUserUid()).isNotNull();
    }

    @Test
    public void selectUserByUuid() throws Exception {
    }

    @Test
    public void updateUser() throws Exception {
    }

    @Test
    public void deleteUserByUuid() throws Exception {
    }

    @Test
    public void insertUser() throws Exception {
    }

}