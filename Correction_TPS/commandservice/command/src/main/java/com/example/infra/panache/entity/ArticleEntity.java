package com.example.infra.panache.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;



@Entity
@Data
public class ArticleEntity extends PanacheEntity {
    public String articleId;
    public int quantite;

}
