package com.example.adapter.rest.restclient;

import com.example.adapter.rest.dto.AuthorDTO;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient
@Path("/api/v1/author")
public interface AuthorClientService {
    @Path("/{id}")
    @GET
    public AuthorDTO get(@PathParam("id") Long id);
}
