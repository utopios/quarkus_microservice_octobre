package com.example.adapter.rest.resource;

import com.example.adapter.dto.CommandeDTO;
import com.example.adapter.factory.CommandeServiceFactory;
import com.example.adapter.helper.CommandeMapper;
import com.example.domain.dto.CommandeDetailsDTO;
import com.example.domain.entity.Commande;
import com.example.domain.service.CommandeService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@Path("/commandes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CommandeResource {

    @Inject
    CommandeServiceFactory commandeServiceFactory;

    @POST
    @Transactional
    public Response creerCommande(CommandeDTO commandeDTO) {
        CommandeService commandeService = commandeServiceFactory.create();
        Commande nouvelleCommande = commandeService.creerCommande(CommandeMapper.toDomain(commandeDTO));
        return Response.ok(CommandeMapper.toDTO(nouvelleCommande)).build();
    }

    @GET
    public List<CommandeDTO> listerCommandes() {
        CommandeService commandeService = commandeServiceFactory.create();
        return commandeService.listerCommandes().stream()
                .map(CommandeMapper::toDTO)
                .collect(Collectors.toList());
    }

    @PUT
    @Path("/{id}")
    public Response modifierCommande(@PathParam("id") Long id, CommandeDTO nouvelleCommandeDTO) {
        CommandeService commandeService = commandeServiceFactory.create();
        Commande commandeModifiee = commandeService.modifierCommande(id, CommandeMapper.toDomain(nouvelleCommandeDTO));
        return Response.ok(CommandeMapper.toDTO(commandeModifiee)).build();
    }

    @DELETE
    @Path("/{id}")
    public Response supprimerCommande(@PathParam("id") Long id) {
        CommandeService commandeService = commandeServiceFactory.create();
        commandeService.supprimerCommande(id);
        return Response.noContent().build();
    }

    @POST
    @Path("/{id}/annuler")
    public Response annulerCommande(@PathParam("id") Long id) {
        CommandeService commandeService = commandeServiceFactory.create();
        commandeService.annulerCommande(id);
        return Response.ok().build();
    }

    @GET
    @Path("/{commandeId}/details")
    public CommandeDetailsDTO recupererDetailsCommande(@PathParam("commandeId") Long commandeId) {
        CommandeService commandeService = commandeServiceFactory.create();
        return commandeService.recupererDetailsCommande(commandeId);
    }
}
