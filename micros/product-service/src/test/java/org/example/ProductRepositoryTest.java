package org.example;


import io.quarkus.test.junit.QuarkusTest;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
public class ProductRepositoryTest {

    @Inject
    ProductRepository productRepository;

    @Test
    @Transactional
    public void testAddProduct() {
        Product product = new Product(null, "Test Product", "Description", 100.0, true, 10);
        productRepository.persist(product);
        assertNotNull(product.getId()); // L'ID doit être généré après persistance
    }

    @Test
    public void testFindProductById() {

        Product product = productRepository.findById(1L);
        assertNotNull(product);
        assertEquals(1L, product.getId());
    }
}
