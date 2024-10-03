package com.example;

import com.example.BookClientService;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

@Path("/client/books")
public class BookClientResource {

    @Inject
    BookClientService bookClientService;

    @POST
    @Path("/add")
    public Uni<Void> addBook() {
        return bookClientService.addBook(1L, "New Book Title", "New Book Author");
    }

    @GET
    @Path("/stream")
    public Uni<Void> streamBooks() {
        return bookClientService.streamBooks();
    }
}