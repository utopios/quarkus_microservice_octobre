package com.example.resource;

import com.example.entity.Book;
import com.example.service.BookService;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;


@Path("books")
public class BookResource {


    @Inject
    private BookService bookService;
    @POST
    public Uni<Void> addBook(Book book) {
        return bookService.addBook(book.title, book.author, book.id);
    }

    @GET
    public Multi<Book> getAllBooks() {
        return bookService.findAllBooks();
    }
}
