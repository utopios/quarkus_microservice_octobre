package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Review {

    private Long id;
    private Long productId; // Référence au produit
    private String customerName;
    private int rating; // Note de 1 à 5
    private String comment;
}
