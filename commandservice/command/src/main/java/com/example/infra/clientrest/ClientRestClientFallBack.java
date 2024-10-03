package com.example.infra.clientrest;

import com.example.domain.dto.ClientDTO;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.faulttolerance.ExecutionContext;
import org.eclipse.microprofile.faulttolerance.FallbackHandler;

@ApplicationScoped
public class ClientRestClientFallBack implements FallbackHandler<ClientDTO> {
    @Override
    public ClientDTO handle(ExecutionContext executionContext) {

        //Logique pour gérer le fallback
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setClientId("unknown");
        clientDTO.setNom("unknown");
        clientDTO.setAdresse("unknown");
        return clientDTO;
    }
}
