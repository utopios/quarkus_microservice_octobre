package com.example.service;


import com.example.entity.Book;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.pgclient.PgPool;
import io.vertx.mutiny.sqlclient.RowSet;
import io.vertx.mutiny.sqlclient.Tuple;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.hibernate.reactive.mutiny.Mutiny;
import io.vertx.mutiny.sqlclient.Row;


import java.util.ArrayList;
import java.util.List;
@ApplicationScoped
public class BookService {

    @Inject
    Mutiny.SessionFactory sessionFactory;  // Injection de la session Hibernate réactive

    @Inject
    PgPool client;

    public Multi<Book> findAllBooks() {
       return client.query("SELECT id, title, author FROM book")
               .execute().onItem()
               .transformToMulti(rows ->  rows.toMulti().onItem()
                       .transform(row -> new Book(row.getLong("id"), row.getString("title"), row.getString("author"))));
    }


    private Multi<Book> getBooks(RowSet<Row> rows) {
        List<Book> books = new ArrayList<>();
        for(Row row: rows) {
            books.add(new Book(row.getLong("id"), row.getString("title"), row.getString("author")));
        }
        return Multi.createFrom().iterable(books);
    }

    /*public Multi<Book> streamAllBook() {
        return Multi.createFrom().deferred(() -> {
            return sessionFactory.openSession().onItem()
                    .transformToMulti(session -> session.createQuery("from Book ", Book.class).getResultList()
                            .onItem().transformToMulti(books -> Multi.createFrom().items(books))
                    );

        })
    }*/

    public Uni<Void> addBook(String title, String author, Long id) {
        // Insertion d'un nouveau livre dans la base de données
        return client
                .preparedQuery("INSERT INTO book (id, title, author, isavailable) VALUES ($1, $2, $3, true)")
                .execute(Tuple.of(id, title, author))
                .onItem().ignore().andContinueWithNull();
    }
}
