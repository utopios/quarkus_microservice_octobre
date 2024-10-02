package com.example.adapter.rest.resource;


import com.example.adapter.rest.dto.AuthorDTO;
import com.example.adapter.rest.dto.QuoteDTO;
import com.example.adapter.rest.restclient.AuthorClientService;
import com.example.domain.QuoteService;
import com.example.domain.entity.Quote;
import com.example.domain.port.QuoteRepository;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Path("/api/v1/quote")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class QuoteResource {


    private final QuoteRepository quoteRepository;
    private final QuoteService quoteService;

    @RestClient
    @Inject
    private AuthorClientService authorClientService;

    public QuoteResource(QuoteRepository quoteRepository/* ,AuthorClientService authorClientService*/) {
        this.quoteRepository = quoteRepository;
        quoteService = new QuoteService(quoteRepository);
        //this.authorClientService = authorClientService;
    }

    @POST
    @Transactional
    public QuoteDTO post(QuoteDTO quote) {
        Quote quoteCreated = quoteService.save(quote.getContent(), quote.getAuthorId());
        return QuoteDTO.builder().id(quoteCreated.getId())
                .content(quoteCreated.getContent())
                .authorId(quote.getAuthorId())
                .build();
    }

    @GET
    @Path("/{id}")
    public QuoteDTO get(@PathParam("id") Long id) {
        Quote quote = quoteService.findById(id);
        return  QuoteDTO.builder().id(quote.getId()).authorDTO(
            getAuthor(quote.getAuthorId())
        ).content(quote.getContent()).build();
    }

    //@Retry(maxRetries = 3)
    @Timeout(1000)
    @Fallback(fallbackMethod = "defaultAuthor")
    @CircuitBreaker(requestVolumeThreshold = 2, failureRatio = 1, delay = 120000)
    public AuthorDTO getAuthor(Long id) {
        return  authorClientService.get(id);
    }

    public AuthorDTO defaultAuthor(Long id) {
        return AuthorDTO.builder().build();
    }
}
