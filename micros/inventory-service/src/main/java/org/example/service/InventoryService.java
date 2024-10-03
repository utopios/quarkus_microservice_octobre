package org.example.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.example.entity.ProductInventory;
import org.example.kafka.InventoryKafkaProducer;
import org.example.repository.ProductInventoryRepository;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class InventoryService {

    @Inject
    ProductInventoryRepository productInventoryRepository;


    @Inject
    InventoryKafkaProducer inventoryKafkaProducer;


    public Optional<ProductInventory> findByProductId(Long productId) {
        return productInventoryRepository.findByProductId(productId);
    }

    public List<ProductInventory> findAll() {
        List<ProductInventory> inventories = productInventoryRepository.listAll();
        return inventories;
    }

    @Transactional
    public ProductInventory createInventory(Long productId, int quantity) {
        ProductInventory productInventory = new ProductInventory();
        productInventory.setProductId(productId);
        productInventory.setQuantity(quantity);
        productInventoryRepository.persist(productInventory);
        return productInventory;
    }

    @Transactional
    public ProductInventory updateInventory(Long id, int quantity) {
        ProductInventory productInventory = productInventoryRepository.findById(id);
        if (productInventory != null) {
            productInventory.setQuantity(quantity);
            productInventoryRepository.persist(productInventory);
        }
        return productInventory;
    }

    @Transactional
    public boolean deleteInventory(Long id) {
        return productInventoryRepository.deleteById(id);
    }


    @Transactional
    public void increaseInventory(Long productId, int quantity) {
        Optional<ProductInventory> productInventoryOpt = productInventoryRepository.findByProductId(productId);

        if (productInventoryOpt.isPresent()) {
            ProductInventory productInventory = productInventoryOpt.get();
            productInventory.setQuantity(productInventory.getQuantity() + quantity);
            productInventoryRepository.persist(productInventory);

            inventoryKafkaProducer.publishStockIncreaseEvent(productId,quantity);

        }
    }

    @Transactional
    public ProductInventory createOrUpdateInventory(Long productId, int quantity) {
        Optional<ProductInventory> productInventoryOpt = findByProductId(productId);

        ProductInventory productInventory;
        if (productInventoryOpt.isPresent()) {
            productInventory = productInventoryOpt.get();
            productInventory.setQuantity(quantity);
        } else {
            productInventory = new ProductInventory();
            productInventory.setProductId(productId);
            productInventory.setQuantity(quantity);
        }
        productInventoryRepository.persist(productInventory);
        return productInventory;
    }
}
