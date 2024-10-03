package org.example.kafka;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

@ApplicationScoped
public class ClientStatusProducer {

    @Inject
    @Channel("client-status")
    Emitter<String> clientStatusEmitter;

    public void sendClientStatus(String clientId, String status) {
        // Construire le message JSON pour le statut du client
        String message = "{\"clientId\": \"" + clientId + "\", \"status\": \"" + status + "\"}";
        // Envoyer le message à Kafka
        clientStatusEmitter.send(message);
    }
}
