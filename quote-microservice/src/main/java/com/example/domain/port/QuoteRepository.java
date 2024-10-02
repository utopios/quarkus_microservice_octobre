package com.example.domain.port;

import com.example.domain.entity.Quote;

public interface QuoteRepository {

    void save(Quote quote);

    Quote findRandom();

    Quote findById(Long id);
}
