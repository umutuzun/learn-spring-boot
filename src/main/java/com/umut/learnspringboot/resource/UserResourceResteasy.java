package com.umut.learnspringboot.resource;

import com.umut.learnspringboot.model.User;
import com.umut.learnspringboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import java.util.List;
import java.util.Optional;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

/**
 * Created by Umut Uzun on 2/6/2018.
 */

@Component
@Path("api/v1/users")
public class UserResourceResteasy {

    private UserService userService;

    @Autowired
    public UserResourceResteasy(UserService userService) {
        this.userService = userService;
    }

    @GET
    @Produces(APPLICATION_JSON)
    public List<User> fetchUsers(@QueryParam("gender") String gender) {
        return userService.getAllUsers(Optional.ofNullable(gender));
    }
}
