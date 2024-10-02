package com.ib.entity;

import com.ib.dto.ContactDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Contact {
    @Id
    @GeneratedValue
    private Long id;
    private String firstname;
    private String lastname;
    private String mail;
    private String phone;

    public ContactDTO toDTO() {
        return  ContactDTO.builder()
                .firstname(firstname)
                .lastname(lastname)
                .id(id)
                .phone(phone)
                .mail(mail)
                .build();
    }
}
