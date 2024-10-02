package com.example.adapter.rest.resource;


import com.example.adapter.rest.dto.AuthorDTO;
import com.example.domain.AuthorService;
import com.example.domain.entity.Author;
import com.example.domain.spi.port.AuthorRepository;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path("/api/v1/author")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthorResource {

    private final AuthorRepository authorRepository;
    private final AuthorService authorService;

    public AuthorResource(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
        this.authorService = new AuthorService(this.authorRepository);
    }

    @POST
    @Transactional
    public AuthorDTO post(AuthorDTO author) {
        Author authorSaved = authorService.save(author.getFirstname(), author.getLastname());
        return AuthorDTO.builder().id(authorSaved.getId())
                .lastname(authorSaved.getLastname())
                .firstname(authorSaved.getFirstname()).build();
    }
    @GET
    @Path("/{id}")
    public AuthorDTO get(@PathParam("id") Long id) {
        Author author = authorService.findById(id);
        return AuthorDTO.builder().id(author.getId())
                .lastname(author.getLastname())
                .firstname(author.getFirstname()).build();
    }
}
