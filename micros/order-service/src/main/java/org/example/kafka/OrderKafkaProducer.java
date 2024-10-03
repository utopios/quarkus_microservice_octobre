package org.example.kafka;

import jakarta.inject.Inject;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

public class OrderKafkaProducer {

    @Inject
    @Channel("order-requests")
    Emitter<String> orderEmitter;

    public void sendOrder(Long orderId, Long clientId, int quantity, int price) {

        String message = "{\"orderId\": \"" + orderId + "\", \"clientId\": \"" + clientId + "\", \"amount\": " + quantity * price + "}";

        orderEmitter.send(message);
    }



}
