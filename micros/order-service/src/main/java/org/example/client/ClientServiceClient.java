package org.example.client;


import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.example.dto.ClientDto;

@Path("/clients")
@RegisterRestClient(configKey = "client-service")
public interface ClientServiceClient {

    @GET
    @Path("/{id}")
    ClientDto getClientById(@PathParam("id") Long id);


}
