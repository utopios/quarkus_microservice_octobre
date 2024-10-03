package com.example.restclient;

import com.example.entity.Book;
import io.smallrye.mutiny.Multi;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;



@Path("/books")
@RegisterRestClient
public interface BookClient {
    @GET
    @Path("/stream")
    @Produces(MediaType.SERVER_SENT_EVENTS)
    Multi<Book> streamBooks();
}
