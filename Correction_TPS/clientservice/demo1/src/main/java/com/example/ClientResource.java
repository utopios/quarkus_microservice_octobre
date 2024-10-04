package com.example;


import com.example.entity.Client;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

@Path("clients")
public class ClientResource {


    @GET
    @Path("/{clientId}")
    public Client get(@PathParam("clientId") Long clientId) {
        Client client = new Client();
        client.setClientId(clientId.toString());
        client.setNom("Vrai client");
        return client;
    }
}
