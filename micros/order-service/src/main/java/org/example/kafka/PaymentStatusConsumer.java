package org.example.kafka;

import org.example.entity.Order;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.example.repository.OrderRepository;

@ApplicationScoped
public class PaymentStatusConsumer {

    @Inject
    OrderRepository orderRepository;

    @Incoming("payment-status")
    @Transactional
    public void onPaymentStatus(String statusMessage) {
        String orderId = statusMessage.split(",")[0].split(":")[1].replace("\"", "").trim();
        String status = statusMessage.split(",")[1].split(":")[1].replace("\"", "").trim();

        Order order = orderRepository.find("id", orderId).firstResult();
        if (order != null) {
            order.setStatus(status);
            orderRepository.persist(order);
        }

        System.out.println("Updated order " + orderId + " to status: " + status);
    }
}