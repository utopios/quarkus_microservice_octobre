package com.example.service;


import com.example.entity.Book;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.pgclient.PgPool;
import io.vertx.mutiny.sqlclient.Tuple;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class LogicBookService {


    @Inject
    PgPool client;


    public Multi<Book> streamBooks() {
        return streamPage(0);
    }

    private static final int PAGE_SIZE = 10;

    private Multi<Book> streamPage(int offset) {
        return client.preparedQuery("SELECT id, title, author FROM book LIMIT $1 OFFSET $2")
                .execute(Tuple.of(PAGE_SIZE, offset))
                .onItem().transformToMulti(rows -> {
                    Multi<Book> currentPage = rows.toMulti()
                            .onItem().transform(row -> new Book(
                                    row.getLong("id"),
                                    row.getString("title"),
                                    row.getString("author")
                            ));
                    if (rows.rowCount() < PAGE_SIZE) {
                        return currentPage;
                    } else {
                        return Multi.createBy().concatenating().streams(currentPage, streamPage(offset + PAGE_SIZE));
                    }
                });
    }

    public Uni<Void> addBook(String title, String author, Long id) {
        // Insertion d'un nouveau livre dans la base de données
        return client
                .preparedQuery("INSERT INTO book (id, title, author, isavailable) VALUES ($1, $2, $3, true)")
                .execute(Tuple.of(id, title, author))
                .onItem().ignore().andContinueWithNull();
    }
}
