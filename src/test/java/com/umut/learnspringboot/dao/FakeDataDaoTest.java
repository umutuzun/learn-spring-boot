package com.umut.learnspringboot.dao;

import com.umut.learnspringboot.model.User;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
    public void shouldSelectUserByUuid() throws Exception {
        UUID gomisUserUid = UUID.randomUUID();
        User gomis = new User(gomisUserUid, "Bafetimbi", "Gomis", "bgomis@example.com",
                User.Gender.MALE, 32);
        fakeDataDao.insertUser(gomisUserUid, gomis);
        assertThat(fakeDataDao.selectAllUsers()).hasSize(2);

        Optional<User> gomisOptional = fakeDataDao.selectUserByUuid(gomisUserUid);
        assertThat(gomisOptional.isPresent()).isTrue();
        assertThat(gomisOptional.get()).isEqualToComparingFieldByField(gomis);
    }

    @Test
    public void shouldNotSelectUserByRandomUuid() throws Exception {
        Optional<User> userOptional = fakeDataDao.selectUserByUuid(UUID.randomUUID());
        assertThat(userOptional.isPresent()).isFalse();
    }

    @Test
    public void shouldUpdateUser() throws Exception {
        UUID umutUid = fakeDataDao.selectAllUsers().get(0).getUserUid();
        User newUmut = new User(umutUid, "Bafetimbi", "Gomis", "bgomis@example.com",
                User.Gender.MALE, 32);
        fakeDataDao.updateUser(newUmut);

        Optional<User> userOptional = fakeDataDao.selectUserByUuid(umutUid);
        assertThat(userOptional.isPresent()).isTrue();

        assertThat(fakeDataDao.selectAllUsers()).hasSize(1);
        assertThat(userOptional.get()).isEqualToComparingFieldByField(newUmut);
    }

    @Test
    public void shouldDeleteUserByUuid() throws Exception {
        UUID umutUid = fakeDataDao.selectAllUsers().get(0).getUserUid();
        fakeDataDao.deleteUserByUuid(umutUid);
        assertThat(fakeDataDao.selectUserByUuid(umutUid).isPresent()).isFalse();
        assertThat(fakeDataDao.selectAllUsers()).isEmpty();
    }

    @Test
    public void shouldInsertUser() throws Exception {
        UUID userUid = UUID.randomUUID();
        User user = new User(userUid, "Bafetimbi", "Gomis", "bgomis@example.com",
                User.Gender.MALE, 32);

        fakeDataDao.insertUser(userUid, user);

        List<User> users = fakeDataDao.selectAllUsers();
        assertThat(users).hasSize(2);
        assertThat(fakeDataDao.selectUserByUuid(userUid).get()).isEqualToComparingFieldByField(user);

    }

}