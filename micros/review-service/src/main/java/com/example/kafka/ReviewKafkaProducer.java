package com.example.kafka;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import java.util.concurrent.CompletionStage;

@ApplicationScoped
public class ReviewKafkaProducer {

    @Inject
    @Channel("review-events-out")
    Emitter<String> emitter;

    public CompletionStage<Void> sendReviewEvent(Long productId, int rating) {
        String message = "Product " + productId + " received new rating: " + rating;
        return emitter.send(message);
    }
}
