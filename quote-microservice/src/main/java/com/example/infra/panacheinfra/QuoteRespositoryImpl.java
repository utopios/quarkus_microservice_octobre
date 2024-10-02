package com.example.infra.panacheinfra;

import com.example.domain.entity.Quote;
import com.example.domain.port.QuoteRepository;
import com.example.infra.panacheinfra.entity.QuoteEntity;
import com.example.infra.panacheinfra.repository.QuoteEntityRepository;
import jakarta.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class QuoteRespositoryImpl implements QuoteRepository {

    private final QuoteEntityRepository quoteEntityRepository;

    public QuoteRespositoryImpl(QuoteEntityRepository quoteEntityRepository) {
        this.quoteEntityRepository = quoteEntityRepository;
    }

    @Override
    public void save(Quote quote) {
        QuoteEntity quoteEntity = QuoteEntity.builder().authorId(quote.getAuthorId())
                .content(quote.getContent()).build();
        quoteEntityRepository.persist(quoteEntity);
        quote.setId(quoteEntity.getId());
    }

    @Override
    public Quote findRandom() {
        return null;
    }

    @Override
    public Quote findById(Long id) {
        QuoteEntity quoteEntity = quoteEntityRepository.findById(id);
        return new Quote(quoteEntity.getId(), quoteEntity.getAuthorId(), quoteEntity.getContent());
    }
}
