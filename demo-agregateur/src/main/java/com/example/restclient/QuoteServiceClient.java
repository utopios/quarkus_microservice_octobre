package com.example.restclient;

import com.example.dto.QuoteDTO;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient
@Path("/api/v1/quote")
public interface QuoteServiceClient {

    @GET
    @Path("/{id}")
    public QuoteDTO get(@PathParam("id") Long id);
}
