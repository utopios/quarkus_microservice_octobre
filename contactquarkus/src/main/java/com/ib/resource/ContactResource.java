package com.ib.resource;


import com.ib.dto.ContactDTO;
import com.ib.entity.Contact;
import com.ib.repository.ContactRepository;
import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.infrastructure.Infrastructure;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;

@Path("contact")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ContactResource {

    @Inject
    private ContactRepository contactRepository;

    /*@GET
    public Uni<List<ContactDTO>> get() {
        return
                Uni.createFrom().item( () ->
                contactRepository.listAll().stream().map(Contact::toDTO).collect(Collectors.toList())
                ).runSubscriptionOn(Infrastructure.getDefaultExecutor());
    }*/

    @GET
    public List<ContactDTO> get() {
        return
                contactRepository.listAll().stream().map(Contact::toDTO).collect(Collectors.toList());
    }

    /*@POST
    @Transactional
    public Uni<ContactDTO> post(ContactDTO contactDTO) {
        Contact contact = contactDTO.toEntity();
        contactRepository.persistAndFlush(contact);
        return Uni.createFrom().item(contact.toDTO());
    }*/

    @POST
    @Transactional
    public ContactDTO post(ContactDTO contactDTO) {
        Contact contact = contactDTO.toEntity();
        contactRepository.persistAndFlush(contact);
        return contact.toDTO();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Boolean delete(@PathParam("id") Long id) {
        try {
            Contact contact = contactRepository.findById(id);
            contactRepository.delete(contact);
            return true;
        }catch (Exception ex) {
            return false;
        }
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public ContactDTO update(@PathParam("id") Long id, ContactDTO contactDTO) {
        Contact contact = contactRepository.findById(id);
        contact.setFirstname(contactDTO.getFirstname());
        contact.setLastname(contactDTO.getLastname());
        contact.setPhone(contactDTO.getPhone());
        contact.setMail(contact.getMail());
        contactRepository.persistAndFlush(contact);
        contactDTO.setId(contact.getId());
        return contactDTO;
    }
}
