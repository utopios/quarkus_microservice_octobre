package com.ib.dto;


import com.ib.entity.Contact;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ContactDTO {

    private Long id;
    private String firstname;
    private String lastname;
    private String mail;
    private String phone;

    public Contact toEntity() {
        Contact contact = new Contact();
        contact.setFirstname(firstname);
        contact.setLastname(lastname);
        contact.setPhone(phone);
        contact.setMail(mail);
        return contact;
    }
}
