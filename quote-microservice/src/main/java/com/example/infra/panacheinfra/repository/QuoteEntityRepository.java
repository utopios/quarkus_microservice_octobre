package com.example.infra.panacheinfra.repository;

import com.example.infra.panacheinfra.entity.QuoteEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class QuoteEntityRepository implements PanacheRepository<QuoteEntity> {
}
