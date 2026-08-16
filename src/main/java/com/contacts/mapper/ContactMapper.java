package com.contacts.mapper;

import com.contacts.dto.request.ContactRequest;
import com.contacts.dto.response.ContactResponse;
import com.contacts.entity.Contact;
import org.springframework.stereotype.Component;

@Component
public class ContactMapper {

    public Contact toEntity(ContactRequest request) {

        Contact contact = new Contact();

        contact.setFirstName(request.firstName());
        contact.setLastName(request.lastName());
        contact.setEmail(request.email());
        contact.setPhoneNumber(request.phoneNumber());
        contact.setAddress(request.address());

        return contact;
    }

    public ContactResponse toResponse(Contact contact) {

        return new ContactResponse(
                contact.getId(),
                contact.getFirstName(),
                contact.getLastName(),
                contact.getEmail(),
                contact.getPhoneNumber(),
                contact.getAddress(),
                contact.getCreatedAt(),
                contact.getUpdatedAt()
        );
    }
}