package com.example.infra.clientrest;

import com.example.domain.dto.ClientDTO;
import com.example.domain.port.ClientServicePort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class ClientServicePortImpl implements ClientServicePort {
    @Inject
    @RestClient
    ClientRestClient clientRestClient;
    @Override
    public ClientDTO recupererClient(String clientId) {
        return clientRestClient.recupererClient(clientId);
    }
}
