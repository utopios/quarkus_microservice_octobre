package org.example.service;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.example.client.ClientServiceClient;
import org.example.client.ProductServiceClient;
import org.example.dto.ClientDto;
import org.example.dto.ProductDto;
import org.example.entity.Order;
import org.example.kafka.OrderKafkaProducer;
import org.example.repository.OrderRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
public class OrderService {


    @Inject
    OrderKafkaProducer orderProducer;

    @Inject
    OrderRepository orderRepository;

    @Inject
    @RestClient
    ProductServiceClient productServiceClient;

    @Inject
    @RestClient
    ClientServiceClient clientServiceClient;


    public List<Order> getAllOrders() {
        List<Order> orders = orderRepository.listAll();
        return orders.stream()
                .map(this::enrichOrderWithDetails)
                .collect(Collectors.toList());
    }

    public Order getOrderById(Long id) {
        return Optional.ofNullable(orderRepository.findById(id))
                .map(this::enrichOrderWithDetails)
                .orElseThrow(() -> new WebApplicationException("Order not found", 404));
    }

    @Transactional
    public Response createOrder(Order order) {
        validateOrder(order);
        order.setOrderDate(LocalDateTime.now());
        // Vérifier si le client est bloqué
        ClientDto client = clientServiceClient.getClientById(order.getClientId());
        if (client != null && client.getIsBlocked()) {
            return Response.status(Response.Status.FORBIDDEN)
                    .entity("Client is blocked and cannot place new orders.").build();
        }

        // Persister la commande dans la base de données
        order.setStatus("PENDING");
        orderRepository.persist(order);

        // Utiliser OrderProducer pour envoyer un message à Kafka
        orderProducer.sendOrder(order.getId(), order.getClientId(), order.getQuantity(), order.getPrice());

        // La réponse est retournée avec le statut "PENDING". Le paiement sera traité de manière asynchrone.

        Order order1 = enrichOrderWithDetails(order);
        return Response.accepted(order1).build();

    }

    @Transactional
    public Order updateOrder(Long id, Order order) {
        validateOrder(order);

        return Optional.ofNullable(orderRepository.findById(id))
                .map(existingOrder -> {
                    existingOrder.setQuantity(order.getQuantity());
                    existingOrder.setOrderDate(order.getOrderDate());
                    orderRepository.persist(existingOrder);
                    return enrichOrderWithDetails(existingOrder);
                })
                .orElseThrow(() -> new WebApplicationException("Order not found", 404));
    }

    @Transactional
    public void deleteOrder(Long id) {
        Optional.ofNullable(orderRepository.findById(id))
                .ifPresentOrElse(orderRepository::delete,
                        () -> { throw new WebApplicationException("Order not found", 404); });
    }

    public List<Order> getOrdersByClientId(Long clientId) {
        if (clientId == null) {
            throw new WebApplicationException("Client ID is required", 400);
        }

       List<Order> orders = orderRepository.getOrderByIdClient(clientId);

        if (orders.isEmpty()) {
            throw new WebApplicationException("No orders found for client ID " + clientId, 404);
        }

        return orders.stream()
                .map(this::enrichOrderWithDetails)
                .collect(Collectors.toList());
    }

    private Order enrichOrderWithDetails(Order order) {
        if (order != null) {
            ClientDto client = clientServiceClient.getClientById(order.getClientId());
            ProductDto product = productServiceClient.getProductById(order.getProductId());

            if (client == null) {
                throw new WebApplicationException("Client not found for ID " + order.getClientId(), 404);
            }

            if (product == null) {
                throw new WebApplicationException("Product not found for ID " + order.getProductId(), 404);
            }

            order.setClientDto(client);
            order.setProductDto(product);
        }
        return order;
    }

    private void validateOrder(Order order) {
        if (order == null) {
            throw new WebApplicationException("Order cannot be null", 400);
        }

        if (order.getClientId() == null) {
            throw new WebApplicationException("Client ID cannot be null", 400);
        }

        if (order.getProductId() == null) {
            throw new WebApplicationException("Product ID cannot be null", 400);
        }

        if (order.getQuantity() <= 0) {
            throw new WebApplicationException("Quantity must be greater than 0", 400);
        }
    }




}
