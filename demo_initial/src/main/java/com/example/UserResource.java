package com.example;


import com.example.service.UserService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path("user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource
{

    //@Inject
    private final UserService userService;

    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @POST
    public void post() {

    }

    @GET
    public String get() {
        return "Get Fom user resource";
    }

    @GET
    @Path("/{id}")
    public void get(@PathParam("id") Long id) {

    }
}
