package com.example.domain.port;

import com.example.domain.entity.Commande;
import java.util.List;
public interface CommandeRepositoryPort {
    Commande sauvegarderCommande(Commande commande);
    Commande recupererCommandeParId(Long id);
    List<Commande> listerToutesLesCommandes();
    void supprimerCommande(Long id);
}
