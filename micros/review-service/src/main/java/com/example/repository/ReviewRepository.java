package com.example.repository;

import com.example.entity.Review;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ReviewRepository implements PanacheRepository<Review> {
}
