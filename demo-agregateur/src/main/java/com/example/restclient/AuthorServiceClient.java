package com.example.restclient;

import com.example.dto.AuthorDTO;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient
@Path("/api/v1/author")
public interface AuthorServiceClient {

    @GET
    @Path("/{id}")
    public AuthorDTO get(@PathParam("id") Long id);
}
