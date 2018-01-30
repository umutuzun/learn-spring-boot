package com.umut.learnspringboot.service;

import com.umut.learnspringboot.dao.FakeDataDao;
import com.umut.learnspringboot.model.User;

import jersey.repackaged.com.google.common.collect.ImmutableList;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.BDDMockito.given;
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
    public void updateUser() {
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
    public void removeUser() {
    }

    @Test
    public void insertUser() {
    }


    private void assertUserFields(User user) {
        assertThat(user.getAge()).isEqualTo(32);
        assertThat(user.getFirstName()).isEqualTo("Bafetimbi");
        assertThat(user.getLastName()).isEqualTo("Gomis");
        assertThat(user.getGender()).isEqualTo(User.Gender.MALE);
        assertThat(user.getEmail()).isEqualTo("bgomis@example.com");
        assertThat(user.getUserUid()).isNotNull();
    }
}