package com.example.adapter.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class CommandeDTO {
    public Long id;
    public String clientId;
    public List<ArticleDTO> articles;
    public String statut;
    public LocalDateTime dateCreation;
    public LocalDateTime dateMiseAJour;


}
