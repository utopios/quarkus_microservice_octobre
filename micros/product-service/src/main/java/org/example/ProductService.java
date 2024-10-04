package org.example;



import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.example.dto.ProductWithReviews;
import org.example.dto.Review;
import org.example.restclient.ReviewServiceClient;

import java.util.Collections;
import java.util.List;

@ApplicationScoped
public class ProductService {

    @Inject
    ProductRepository productRepository;

    @Inject
    @RestClient
    ReviewServiceClient reviewService;
    public List<Product> getAllProducts() {
        return productRepository.listAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id);
    }

    @Transactional
    public Product createProduct(Product product) {
        productRepository.persist(product);
        return product;
    }

    @Transactional
    public Product updateProduct(Long id, Product product) {
        Product entity = productRepository.findById(id);
        if (entity != null) {
            entity.setName(product.getName());
            entity.setDescription(product.getDescription());
            entity.setPrice(product.getPrice());
        }
        return entity;
    }

    @Fallback(fallbackMethod = "getDefaultReviews")
    @Timeout(2000)
    @CircuitBreaker(requestVolumeThreshold = 4, failureRatio = 0.5, delay = 10000)
    public ProductWithReviews getProductWithReviews(Long id) {
        Product product = productRepository.findById(id);
        List<Review> reviews = reviewService.getReviewsByProductId(id);
        return new ProductWithReviews(product, reviews);
    }

    public ProductWithReviews getDefaultReviews(Long id) {
        Product product = productRepository.findById(id);
        List<Review> defaultReviews = Collections.singletonList(new Review(null, id, "N/A", 0, "No reviews available"));
        return new ProductWithReviews(product, defaultReviews);
    }

    @Transactional
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}

