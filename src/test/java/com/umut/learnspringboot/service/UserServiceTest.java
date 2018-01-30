package com.umut.learnspringboot.service;

import com.umut.learnspringboot.dao.FakeDataDao;
import com.umut.learnspringboot.model.User;

import jersey.repackaged.com.google.common.collect.ImmutableList;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;

public class UserServiceTest {

    @Mock
    private FakeDataDao fakeDataDao;

    private UserService userService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        userService = new UserService(fakeDataDao);
    }

    @Test
    public void shouldGetAllUsers() {

        UUID gomisUid = UUID.randomUUID();
        User gomis = new User(gomisUid, "Bafetimbi", "Gomis", "bgomis@example.com",
                User.Gender.MALE, 32);

        ImmutableList<User> users = new ImmutableList.Builder<User>()
                .add(gomis).build();

        given(fakeDataDao.selectAllUsers()).willReturn(users);

        List<User> allUsers = userService.getAllUsers();

        assertThat(allUsers).hasSize(1);

        User user = allUsers.get(0);

        assertUserFields(user);
    }

    @Test
    public void shouldGetUser() {

        UUID gomisUid = UUID.randomUUID();
        User gomis = new User(gomisUid, "Bafetimbi", "Gomis", "bgomis@example.com",
                User.Gender.MALE, 32);

        given(fakeDataDao.selectUserByUuid(gomisUid)).willReturn(Optional.of(gomis));

        Optional<User> userOptional = userService.getUser(gomisUid);

        assertThat(userOptional.isPresent()).isTrue();
        User user = userOptional.get();

        assertUserFields(user);

    }

    @Test
    public void shouldUpdateUser() {

        UUID gomisUid = UUID.randomUUID();
        User gomis = new User(gomisUid, "Bafetimbi", "Gomis", "bgomis@example.com",
                User.Gender.MALE, 32);

        given(fakeDataDao.selectUserByUuid(gomisUid)).willReturn(Optional.of(gomis));
        given(fakeDataDao.updateUser(gomis)).willReturn(1);

        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);

        int updateResult = userService.updateUser(gomis);

        verify(fakeDataDao).selectUserByUuid(gomisUid);
        verify(fakeDataDao).updateUser(captor.capture());

        User user = captor.getValue();
        assertUserFields(user);

        assertThat(updateResult).isEqualTo(1);

    }

    @Test
    public void shouldRemoveUser() {

        UUID gomisUid = UUID.randomUUID();
        User gomis = new User(gomisUid, "Bafetimbi", "Gomis", "bgomis@example.com",
                User.Gender.MALE, 32);

        given(fakeDataDao.selectUserByUuid(gomisUid)).willReturn(Optional.of(gomis));
        given(fakeDataDao.deleteUserByUuid(gomisUid)).willReturn(1);

        int deleteResult = userService.removeUser(gomisUid);

        verify(fakeDataDao).selectUserByUuid(gomisUid);
        verify(fakeDataDao).deleteUserByUuid(gomisUid);

        assertThat(deleteResult).isEqualTo(1);

    }

    @Test
    public void shouldInsertUser() {
        User gomis = new User(null, "Bafetimbi", "Gomis", "bgomis@example.com",
                User.Gender.MALE, 32);

        given(fakeDataDao.insertUser(any(UUID.class), eq(gomis))).willReturn(1);

        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);

        int insertResult = userService.insertUser(gomis);

        verify(fakeDataDao).insertUser(any(UUID.class), captor.capture());

        User user = captor.getValue();

        assertUserFields(user);

        assertThat(insertResult).isEqualTo(1);
    }


    private void assertUserFields(User user) {
        assertThat(user.getAge()).isEqualTo(32);
        assertThat(user.getFirstName()).isEqualTo("Bafetimbi");
        assertThat(user.getLastName()).isEqualTo("Gomis");
        assertThat(user.getGender()).isEqualTo(User.Gender.MALE);
        assertThat(user.getEmail()).isEqualTo("bgomis@example.com");
        assertThat(user.getUserUid()).isInstanceOf(UUID.class);
    }
}