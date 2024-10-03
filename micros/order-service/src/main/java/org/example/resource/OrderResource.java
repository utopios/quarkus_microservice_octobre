package org.example.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.*;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.example.entity.Order;
import org.example.service.OrderService;

import java.util.List;

@Path("/orders")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OrderResource {

    @Inject
    OrderService orderService;

    @GET
    public List<Order> getAllOrders() {

        return orderService.getAllOrders();
    }

    @GET
    @Path("/{id}")
    public Order getOrderById(@PathParam("id") Long id) {
        return orderService.getOrderById(id);
    }

    @POST
    public Response createOrder(Order order) {

        return orderService.createOrder(order);
    }

    @PUT
    @Path("/{id}")
    public Order updateOrder(@PathParam("id") Long id, Order order) {
        return orderService.updateOrder(id, order);
    }

    @DELETE
    @Path("/{id}")
    public void deleteOrder(@PathParam("id") Long id) {
        orderService.deleteOrder(id);
    }


    @GET
    @Path("/client/{clientId}")
    public List<Order> getOrdersByClientId(@PathParam("clientId") Long clientId) {
        return orderService.getOrdersByClientId(clientId);
    }

}