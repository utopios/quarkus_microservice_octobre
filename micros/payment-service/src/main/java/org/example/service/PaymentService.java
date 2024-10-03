package org.example.service;

import io.smallrye.reactive.messaging.annotations.Channel;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.Response;
import lombok.ToString;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.example.client.AccountServiceClient;
import org.example.dto.PaymentRequest;
import org.example.entity.Payment;
import org.example.kafka.ClientStatusProducer;
import org.example.kafka.PaymentStatusProducer;
import org.example.repository.PaymentRepository;

import java.util.List;


@ApplicationScoped
public class PaymentService {


    @Inject
    PaymentRepository paymentRepository;

    @Inject
    PaymentStatusProducer paymentStatusProducer;

    @Inject
    ClientStatusProducer clientStatusProducer;

    @Inject
    @RestClient
    AccountServiceClient accountServiceClient;

    @Transactional
    public void processOrderRequest(String orderMessage) {
        // Parse le message JSON pour obtenir orderId, clientId et amount
        String orderId = orderMessage.split(",")[0].split(":")[1].replace("\"", "").trim();

        String clientId = orderMessage.split(",")[1].split(":")[1].replace("\"", "").trim();
        double amount = Double.parseDouble(orderMessage.split(",")[2].split(":")[1].replace("}", "").trim());

        // Créer un nouveau paiement
        Payment payment = new Payment(Long.parseLong(clientId), amount, "PENDING");

        // Tenter de débiter le compte du client
        String response = accountServiceClient.debitAccount(Long.parseLong(clientId), amount);

        if ("Account debited successfully".equals(response)) {
            payment.status = "SUCCESS";
            paymentStatusProducer.sendPaymentStatus(orderId, "SUCCESS");
        } else {
            payment.status = "FAILED";
            paymentStatusProducer.sendPaymentStatus(orderId, "FAILED");
            clientStatusProducer.sendClientStatus(clientId, "BLOCKED");
        }

        // Persister le paiement dans la base de données
        paymentRepository.persist(payment);
    }

    public Payment findPaymentById(Long paymentId) {
        return paymentRepository.findById(paymentId);
    }

    public List<Payment> findPaymentsByClientId(String clientId) {
        return paymentRepository.find("clientId", clientId).list();
    }


    @Transactional
    public boolean processPaymentRequest(PaymentRequest paymentRequest) {
        // Tenter de débiter le compte du client
        String response = accountServiceClient.debitAccount(paymentRequest.getClientId(), paymentRequest.getAmount());
        boolean isSuccess = "Account debited successfully".equals(response);
        Payment payment = new Payment(paymentRequest.getClientId(), paymentRequest.getAmount(), isSuccess ? "SUCCESS" : "FAILED");
        paymentRepository.persist(payment);
        return isSuccess;
    }


}
