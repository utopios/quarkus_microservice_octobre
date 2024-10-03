package org.example.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.example.entity.Payment;

@ApplicationScoped
public class PaymentRepository implements PanacheRepository<Payment> {
    // Vous pouvez ajouter des méthodes personnalisées ici si nécessaire
}