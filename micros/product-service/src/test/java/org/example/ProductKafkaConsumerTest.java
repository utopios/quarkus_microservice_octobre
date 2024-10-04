package org.example;


import org.example.Product;
import org.example.ProductRepository;
import org.example.kafka.ProductKafkaConsumer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

class ProductKafkaConsumerTest {

    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ProductKafkaConsumer productKafkaConsumer;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testUpdateProductStock() {
        String message = "Product 1001 stock increased to 50";

        Product product = new Product(1001L, "Test Product", "Description", 100.0, true, 10,5, 0L);
        when(productRepository.findById(1001L)).thenReturn(product);

        productKafkaConsumer.updateProductStock(message);

        // Vérifier que la quantité a été mise à jour
        verify(productRepository).persist(product);
        assert(product.getQuantity() == 60); // Quantité actuelle +50
    }


}
