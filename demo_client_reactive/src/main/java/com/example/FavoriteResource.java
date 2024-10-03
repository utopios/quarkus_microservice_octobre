package com.example;

import com.example.entity.Book;
import com.example.service.FavoriteService;
import io.smallrye.mutiny.Multi;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/favorite")
public class FavoriteResource {

    @Inject
    FavoriteService favoriteService;
    @GET
    @Produces(MediaType.SERVER_SENT_EVENTS)
    public Multi<Book> hello() {
        favoriteService.fetchAllBooksInStreamWithVoidAction();
        return favoriteService.fetchAllBooksInStream();
    }
}
