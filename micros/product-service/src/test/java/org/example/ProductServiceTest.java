package org.example;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ProductServiceTest {

    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ProductService productService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddProduct() {
        Product product = new Product(1L, "Product 1", "Description", 100.0, true, 100,5,0L);

        // Simuler la méthode persist
        doNothing().when(productRepository).persist(any(Product.class));

        Product result = productService.createProduct(product);

        assertEquals(result.getId(), product.getId());
        verify(productRepository, times(1)).persist(any(Product.class)); // Vérifie que persist est bien appelé
    }
}
