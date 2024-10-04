package com.example.service;

import com.example.entity.Review;
import com.example.kafka.ReviewKafkaProducer;
import com.example.repository.ReviewRepository;
import io.opentelemetry.instrumentation.annotations.WithSpan;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class ReviewService {


    private final ReviewRepository reviewRepository;

    @Inject
    private ReviewKafkaProducer reviewKafkaProducer;
    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @WithSpan(value = "getReviewsByProductId-operation")
    public List<Review> getReviewsByProductId(Long productId) {
        return reviewRepository.list("productId", productId);
    }

    @Transactional
    @WithSpan(value = "createReview-operation")
    public Review createReview(Review review) {
        reviewRepository.persist(review);
        reviewKafkaProducer.sendReviewEvent(review.getProductId(), review.getRating());
        return review;
    }

    @Transactional
    @WithSpan(value = "deleteReview-operation")
    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }
}
