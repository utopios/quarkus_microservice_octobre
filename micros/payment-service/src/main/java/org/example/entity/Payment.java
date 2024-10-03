package org.example.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    public Long clientId;
    public double amount;
    public String status;

    public Payment() {}

    public Payment(Long clientId, double amount, String status) {
        this.clientId = clientId;
        this.amount = amount;
        this.status = status;
    }


}
