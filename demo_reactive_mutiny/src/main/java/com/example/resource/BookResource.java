package com.example.resource;

import com.example.entity.Book;
import com.example.service.BookService;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;


@Path("books")
public class BookResource {


    @Inject
    private BookService bookService;
    @POST
    public Uni<Void> addBook(Book book) {
        return bookService.addBook(book.title, book.author, book.id);
    }

    @GET
    @Produces(MediaType.SERVER_SENT_EVENTS)
    public Multi<Book> getAllBooks() {
        //return bookService.findAllBooks();
        return bookService.streamBooks();
    }
}
