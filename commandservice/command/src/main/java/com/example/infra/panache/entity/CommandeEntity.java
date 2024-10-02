package com.example.infra.panache.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;



import java.time.LocalDateTime;
import java.util.List;
@Entity
@Data
public class CommandeEntity extends PanacheEntity {
    public String clientId;
    public String statut;
    public LocalDateTime dateCreation;
    public LocalDateTime dateMiseAJour;

    @OneToMany(cascade = CascadeType.ALL)
    public List<ArticleEntity> articles;

}
