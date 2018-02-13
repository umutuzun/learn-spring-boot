package com.umut.learnspringboot.resource;

import com.umut.learnspringboot.model.User;
import com.umut.learnspringboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

/**
 * Created by Umut Uzun on 2/6/2018.
 */

@Component
@Validated
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

    @GET
    @Produces(APPLICATION_JSON)
    @Path("{userUid}")
    public User fetchUser(@PathParam("userUid")UUID userUid) {
        return userService.getUser(userUid)
                .orElseThrow(() -> new NotFoundException("user " + userUid + " not found"));

        //functional equivalent
        //return userService.getUser(userUid).<ResponseEntity>map(ResponseEntity::ok)
        //        .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
        //                .body(new ErrorMessage("user " + userUid + " was not found.")));
    }

    @POST
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public void insertNewUser(@Valid User user) {
        int result = userService.insertUser(user);
    }

    @PUT
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public void updateUser(User user) {
        userService.updateUser(user);
    }

    @DELETE
    @Produces(APPLICATION_JSON)
    @Path("{userUid}")
    public void deleteUser(@PathParam("userUid") UUID userUid) {
        userService.removeUser(userUid);
    }

}
