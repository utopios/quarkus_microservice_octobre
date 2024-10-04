package com.example.infra.panache.repository.impl;

import com.example.domain.entity.Article;
import com.example.domain.entity.Commande;
import com.example.domain.entity.StatutCommande;
import com.example.domain.port.CommandeRepositoryPort;
import com.example.infra.panache.entity.ArticleEntity;
import com.example.infra.panache.entity.CommandeEntity;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class CommandeRepositoryPortImpl implements CommandeRepositoryPort {

    @Override
    public Commande sauvegarderCommande(Commande commande) {
        CommandeEntity entity = mapToEntity(commande);
        entity.persist();
        return mapToDomain(entity);
    }

    @Override
    public Commande recupererCommandeParId(Long id) {
        CommandeEntity entity = CommandeEntity.findById(id);
        return mapToDomain(entity);
    }

    @Override
    public List<Commande> listerToutesLesCommandes() {
        List<CommandeEntity> entities = CommandeEntity.listAll();
        return entities.stream().map(this::mapToDomain).collect(Collectors.toList());
    }

    @Override
    public void supprimerCommande(Long id) {
        CommandeEntity.deleteById(id);
    }

    // Mapping de Commande vers CommandeEntity
    private CommandeEntity mapToEntity(Commande commande) {
        CommandeEntity entity = new CommandeEntity();
        entity.clientId = commande.getClientId();
        entity.statut = commande.getStatut().toString();
        entity.dateCreation = commande.getDateCreation();
        entity.dateMiseAJour = commande.getDateMiseAJour();
        entity.articles = commande.getArticles().stream()
                .map(this::mapToEntity)
                .collect(Collectors.toList());
        return entity;
    }

    // Mapping de CommandeEntity vers Commande (domaine)
    private Commande mapToDomain(CommandeEntity entity) {
        Commande commande = new Commande();
        commande.setId(entity.id);
        commande.setClientId(entity.clientId);
        commande.setStatut(StatutCommande.valueOf(entity.statut));
        commande.setDateCreation(entity.dateCreation);
        commande.setDateMiseAJour(entity.dateMiseAJour);
        commande.setArticles(entity.articles.stream()
                .map(this::mapToDomain)
                .collect(Collectors.toList()));
        return commande;
    }

    private ArticleEntity mapToEntity(Article article) {
        ArticleEntity entity = new ArticleEntity();
        entity.articleId = article.getArticleId();
        entity.quantite = article.getQuantite();
        return entity;
    }

    private Article mapToDomain(ArticleEntity entity) {
        Article article = new Article();
        article.setArticleId(entity.articleId);
        article.setQuantite(entity.quantite);
        return article;
    }
}