package com.example.service;


import com.example.entity.Book;
import com.example.restclient.BookClient;
import io.smallrye.mutiny.Multi;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class FavoriteService {
    @Inject
    BookClient bookClient;

    public Multi<Book> fetchAllBooksInStream() {
        return bookClient.streamBooks();
    }

    public void fetchAllBooksInStreamWithVoidAction() {
        bookClient.streamBooks().subscribe().with(b -> {
            System.out.println(b.title);
        },failure -> {
            System.out.println(failure.getMessage());
        }, () -> {
            System.out.println("Flux terminé correctement");
        });
    }


}
