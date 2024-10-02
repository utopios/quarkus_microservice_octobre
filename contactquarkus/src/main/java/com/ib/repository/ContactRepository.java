package com.ib.repository;

import com.ib.entity.Contact;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;


@ApplicationScoped
public class ContactRepository implements PanacheRepository<Contact> {


}
