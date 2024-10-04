package com.example.domain.dto;

import com.example.domain.entity.StatutCommande;

import java.time.LocalDateTime;

public class CommandeDetailsDTO {
    private Long commandeId;
    private ClientDTO client;
    private StatutCommande statut;
    private LocalDateTime dateCreation;
    private LocalDateTime dateMiseAJour;

    public void setCommandeId(Long commandeId) {
        this.commandeId = commandeId;
    }

    public Long getCommandeId() {
        return commandeId;
    }

    public void setClient(ClientDTO client) {
        this.client = client;
    }

    public ClientDTO getClient() {
        return client;
    }


    public void setStatut(StatutCommande statut) {
        this.statut = statut;
    }

    public StatutCommande getStatut() {
        return statut;
    }

    public void setDateCreation(LocalDateTime dateCreation) {
        this.dateCreation = dateCreation;
    }

    public LocalDateTime getDateCreation() {
        return dateCreation;
    }

    public void setDateMiseAJour(LocalDateTime dateMiseAJour) {
        this.dateMiseAJour = dateMiseAJour;
    }

    public LocalDateTime getDateMiseAJour() {
        return dateMiseAJour;
    }
}
