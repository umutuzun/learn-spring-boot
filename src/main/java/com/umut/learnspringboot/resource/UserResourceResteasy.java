package com.umut.learnspringboot.resource;

import com.umut.learnspringboot.model.User;
import com.umut.learnspringboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
    public Response fetchUser(@PathParam("userUid")UUID userUid) {
        Optional<User> userOptional = userService.getUser(userUid);
        if(userOptional.isPresent()) {
            return Response.ok(userOptional.get()).build();
        }
        return Response.status(Response.Status.NOT_FOUND.NOT_FOUND).
                entity(new ErrorMessage("user " + userUid + " was not found.")).build();

        //functional equivalent
        //return userService.getUser(userUid).<ResponseEntity>map(ResponseEntity::ok)
        //        .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
        //                .body(new ErrorMessage("user " + userUid + " was not found.")));
    }

    @POST
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Response insertNewUser(User user) {
        int result = userService.insertUser(user);
        return getIntegerResponseEntity(result);
    }

    private Response getIntegerResponseEntity(int result) {
        if(result == 1) {
            return Response.ok().build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @PUT
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Response updateUser(User user) {
        int result = userService.updateUser(user);
        return getIntegerResponseEntity(result);
    }

    @DELETE
    @Produces(APPLICATION_JSON)
    @Path("{userUid}")
    public Response deleteUser(@PathParam("userUid") UUID userUid) {
        int result = userService.removeUser(userUid);
        return getIntegerResponseEntity(result);
    }

}
