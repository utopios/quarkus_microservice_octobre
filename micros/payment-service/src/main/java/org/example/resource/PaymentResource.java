package org.example.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.example.dto.PaymentRequest;
import org.example.entity.Payment;
import org.example.service.PaymentService;

@Path("/payments")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PaymentResource {

    @Inject
    PaymentService paymentService;

    @GET
    @Path("/{id}")
    public Response getPayment(@PathParam("id") Long paymentId) {
        Payment payment = paymentService.findPaymentById(paymentId);
        if (payment != null) {
            return Response.ok(payment).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    public Response createPayment(PaymentRequest paymentRequest) {
        boolean success = paymentService.processPaymentRequest(paymentRequest);
        if (success) {
            return Response.status(Response.Status.CREATED).entity("Payment processed successfully").build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity("Payment processing failed").build();
        }
    }

 
    @GET
    @Path("/client/{clientId}")
    public Response getPaymentsByClientId(@PathParam("clientId") String clientId) {
        return Response.ok(paymentService.findPaymentsByClientId(clientId)).build();
    }
}