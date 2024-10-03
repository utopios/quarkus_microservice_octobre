package com.example.entity;

import io.quarkus.hibernate.reactive.panache.PanacheEntity;
import io.smallrye.mutiny.Uni;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import java.util.List;

@Entity
public class Book extends PanacheEntity {

    @Column(nullable = false)
    public String title;

    @Column(nullable = false)
    public String author;

    public boolean isAvailable = true;

    public Book() {
    }

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.isAvailable = true;
    }

    public Book(Long id, String title, String author) {
        this(title,author);
        this.id = id;
    }

    // Méthode pour trouver tous les livres disponibles
    public static Uni<List<Book>> findAvailableBooks() {
        return Book.find("isAvailable", true).list();
    }
}