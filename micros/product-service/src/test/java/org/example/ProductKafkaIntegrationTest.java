package org.example;



import io.quarkus.test.junit.QuarkusTest;

import io.strimzi.test.container.KafkaContainer;
import jakarta.inject.Inject;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.example.kafka.ProductKafkaConsumer;
import org.junit.jupiter.api.*;

import org.testcontainers.utility.DockerImageName;

import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.wildfly.common.Assert.assertNotNull;

@QuarkusTest
public class ProductKafkaIntegrationTest {

    static KafkaContainer kafkaContainer;

    @Inject
    ProductKafkaConsumer productKafkaConsumer;

    @Inject
    ProductRepository productRepository;

    @BeforeAll
    public static void setupKafka() {
        kafkaContainer = new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:7.0.1"));
        kafkaContainer.start();
    }

    @AfterAll
    public static void stopKafka() {
        kafkaContainer.stop();
    }

    @Test
    public void testKafkaConsumer() {
        // Configurer le producteur Kafka
        Properties props = new Properties();
        props.put("bootstrap.servers", kafkaContainer.getBootstrapServers());
        props.put("key.serializer", StringSerializer.class.getName());
        props.put("value.serializer", StringSerializer.class.getName());

        KafkaProducer<String, String> producer = new KafkaProducer<>(props);
        String message = "Product 1 stock increased to 50";

        producer.send(new ProducerRecord<>("stock-increase", message));

        // Attente active pour donner du temps au consommateur Kafka de traiter le message
        Product product = null;
        int attempts = 0;
        int maxAttempts = 10;  // Limite à 10 tentatives
        while (product == null && attempts < maxAttempts) {
            product = productRepository.findById(1L);
            if (product == null) {
                attempts++;
                try {
                    Thread.sleep(500); // Attendre 500ms entre les tentatives
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        // Assertions pour vérifier que le produit a été mis à jour
        assertNotNull(product);
        assertEquals(60, product.getQuantity(), "La quantité du produit doit être mise à jour à 60");
    }
}