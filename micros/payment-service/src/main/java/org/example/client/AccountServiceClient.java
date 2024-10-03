package org.example.client;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/accounts")
@RegisterRestClient(configKey = "account-service")
public interface AccountServiceClient {

    @POST
    @Path("/debit")
    @Consumes(MediaType.APPLICATION_JSON)
    String debitAccount(@QueryParam("clientId") Long clientId, @QueryParam("amount") double amount);
}
