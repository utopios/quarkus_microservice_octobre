package org.example.kafka;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.example.service.PaymentService;

@ApplicationScoped
public class OrderRequestConsumer {

    @Inject
    PaymentService paymentService;

    @Incoming("order-requests")
    @Transactional
    public void consumeOrderRequest(String orderMessage) {
        // Déléguer le traitement de la commande à PaymentService
        paymentService.processOrderRequest(orderMessage);
    }
}