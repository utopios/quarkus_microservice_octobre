package com.example.domain.service;

import com.example.domain.entity.Commande;
import com.example.domain.entity.StatutCommande;
import com.example.domain.port.CommandeRepositoryPort;

import java.time.LocalDateTime;
import java.util.List;

public class CommandeService {

    private final CommandeRepositoryPort commandeRepository;

    // Le port est injecté via le constructeur (pas d'@Inject ici)
    public CommandeService(CommandeRepositoryPort commandeRepository) {
        this.commandeRepository = commandeRepository;
    }

    public Commande creerCommande(Commande commande) {
        commande.setStatut(StatutCommande.EN_COURS);
        commande.setDateCreation(LocalDateTime.now());
        return commandeRepository.sauvegarderCommande(commande);
    }

    public Commande modifierCommande(Long id, Commande nouvelleCommande) {
        Commande commandeExistante = commandeRepository.recupererCommandeParId(id);
        commandeExistante.setArticles(nouvelleCommande.getArticles());
        commandeExistante.setStatut(nouvelleCommande.getStatut());
        commandeExistante.setDateMiseAJour(LocalDateTime.now());
        return commandeRepository.sauvegarderCommande(commandeExistante);
    }

    public List<Commande> listerCommandes() {
        return commandeRepository.listerToutesLesCommandes();
    }

    public void annulerCommande(Long id) {
        Commande commande = commandeRepository.recupererCommandeParId(id);
        commande.setStatut(StatutCommande.ANNULEE);
        commandeRepository.sauvegarderCommande(commande);
    }

    public void supprimerCommande(Long id) {
        commandeRepository.supprimerCommande(id);
    }
}
