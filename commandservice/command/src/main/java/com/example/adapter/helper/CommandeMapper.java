package com.example.adapter.helper;

import com.example.adapter.dto.ArticleDTO;
import com.example.adapter.dto.CommandeDTO;
import com.example.domain.entity.Article;
import com.example.domain.entity.Commande;
import com.example.domain.entity.StatutCommande;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

public class CommandeMapper {

    public static CommandeDTO toDTO(Commande commande) {
        CommandeDTO dto = new CommandeDTO();
        dto.id = commande.getId();
        dto.clientId = commande.getClientId();
        dto.statut = commande.getStatut().name();
        dto.dateCreation = commande.getDateCreation();
        dto.dateMiseAJour = commande.getDateMiseAJour();
        dto.articles = commande.getArticles().stream()
                .map(CommandeMapper::toDTO)
                .collect(Collectors.toList());
        return dto;
    }

    public static Commande toDomain(CommandeDTO dto) {
        Commande commande = new Commande();
        commande.setId(dto.id);
        commande.setClientId(dto.clientId);
        commande.setDateCreation(LocalDateTime.now());
        commande.setDateMiseAJour(LocalDateTime.now());
        commande.setArticles(dto.articles.stream()
                .map(CommandeMapper::toDomain)
                .collect(Collectors.toList()));
        return commande;
    }

    public static ArticleDTO toDTO(Article article) {
        ArticleDTO dto = new ArticleDTO();
        dto.articleId = article.getArticleId();
        dto.quantite = article.getQuantite();
        return dto;
    }

    public static Article toDomain(ArticleDTO dto) {
        Article article = new Article();
        article.setArticleId(dto.articleId);
        article.setQuantite(dto.quantite);
        return article;
    }
}
