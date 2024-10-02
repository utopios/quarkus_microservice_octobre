package com.example.adapter.factory;

import com.example.domain.port.CommandeRepositoryPort;
import com.example.domain.service.CommandeService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class CommandeServiceFactory {

    @Inject
    CommandeRepositoryPort commandeRepository;

    // Méthode pour fournir une instance de CommandeService
    public CommandeService create() {
        return new CommandeService(commandeRepository);
    }
}
