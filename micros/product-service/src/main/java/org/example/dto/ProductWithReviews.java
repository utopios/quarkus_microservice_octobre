package org.example.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.Product;

import java.util.List;

@Data
@AllArgsConstructor
public class ProductWithReviews {
    private Product product;
    private List<Review> reviews;
}
