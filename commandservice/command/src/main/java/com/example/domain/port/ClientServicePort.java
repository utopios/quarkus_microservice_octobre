package com.example.domain.port;

import com.example.domain.dto.ClientDTO;

public interface ClientServicePort {
    ClientDTO recupererClient(String clientId);
}
