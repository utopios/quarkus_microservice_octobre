package org.example.kafka;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

@ApplicationScoped
public class PaymentStatusProducer {

    @Inject
    @Channel("payment-status")
    Emitter<String> paymentStatusEmitter;

    public void sendPaymentStatus(String orderId, String status) {
        // Construire le message JSON pour le statut de paiement
        String message = "{\"orderId\": \"" + orderId + "\", \"status\": \"" + status + "\"}";
        // Envoyer le message à Kafka
        paymentStatusEmitter.send(message);
    }
}
