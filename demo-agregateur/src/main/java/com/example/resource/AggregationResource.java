package com.example.resource;

import com.example.dto.AuthorDTO;
import com.example.dto.QuoteAuthorDTO;
import com.example.dto.QuoteDTO;
import com.example.restclient.AuthorServiceClient;
import com.example.restclient.QuoteServiceClient;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Path("/api/aggregation")
public class AggregationResource {

    @Inject
    @RestClient
    private AuthorServiceClient authorServiceClient;

    @RestClient
    @Inject
    private QuoteServiceClient quoteServiceClient;

    @GET
    @Path("/{id}")
    public QuoteAuthorDTO get(@PathParam("id") Long id) {
        
        QuoteDTO quoteDTO = quoteServiceClient.get(id);
        
        AuthorDTO authorDTO = authorServiceClient.get(quoteDTO.getAuthorDTO().getId());
        
        return QuoteAuthorDTO.builder().authorDTO(authorDTO).quoteDTO(quoteDTO).build();
    }
}
