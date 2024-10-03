package com.example.infra.clientrest;


import com.example.domain.dto.ClientDTO;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient
@Path("/clients")
public interface ClientRestClient {

    @GET
    @Path("/{clientId}")
    @Timeout(1000)
    //@HeaderParam("Authorization ")
    @Fallback(ClientRestClientFallBack.class)
    @CircuitBreaker(requestVolumeThreshold = 4, failureRatio = 0.7, delay = 300000)
    ClientDTO recupererClient(@PathParam("clientId") String clientId);

}
