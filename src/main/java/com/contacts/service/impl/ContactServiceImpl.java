package com.contacts.service.impl;

import com.contacts.dto.request.ContactPatchRequest;
import com.contacts.dto.request.ContactRequest;
import com.contacts.dto.response.ContactResponse;
import com.contacts.entity.Contact;
import com.contacts.exception.ContactNotFoundException;
import com.contacts.exception.DuplicateContactException;
import com.contacts.mapper.ContactMapper;
import com.contacts.repository.ContactRepository;
import com.contacts.service.ContactService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;
    private final ContactMapper contactMapper;

    public ContactServiceImpl(
            ContactRepository contactRepository,
            ContactMapper contactMapper) {

        this.contactRepository = contactRepository;
        this.contactMapper = contactMapper;
    }

    @Override
    public ContactResponse createContact(ContactRequest request) {

        if (contactRepository.existsByEmail(request.email())) {
            throw new DuplicateContactException(
                    "Contact already exists with email: " + request.email()
            );
        }

        Contact contact = contactMapper.toEntity(request);

        LocalDateTime now = LocalDateTime.now();

        contact.setCreatedAt(now);
        contact.setUpdatedAt(now);

        Contact savedContact = contactRepository.save(contact);

        return contactMapper.toResponse(savedContact);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ContactResponse> getAllContacts() {

        return contactRepository.findAll()
                .stream()
                .map(contactMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ContactResponse getContactById(Long id) {

        Contact contact = contactRepository.findById(id)
                .orElseThrow(() ->
                        new ContactNotFoundException(
                                "Contact not found with id: " + id
                        )
                );

        return contactMapper.toResponse(contact);
    }

    @Override
    public ContactResponse updateContact(
            Long id,
            ContactRequest request) {

        Contact contact = contactRepository.findById(id)
                .orElseThrow(() ->
                        new ContactNotFoundException(
                                "Contact not found with id: " + id
                        )
                );

        if (!contact.getEmail().equalsIgnoreCase(request.email())
                && contactRepository.existsByEmail(request.email())) {

            throw new DuplicateContactException(
                    "Contact already exists with email: " + request.email()
            );
        }

        contact.setFirstName(request.firstName());
        contact.setLastName(request.lastName());
        contact.setEmail(request.email());
        contact.setPhoneNumber(request.phoneNumber());
        contact.setAddress(request.address());
        contact.setUpdatedAt(LocalDateTime.now());

        Contact updatedContact = contactRepository.save(contact);

        return contactMapper.toResponse(updatedContact);
    }

    @Override
    public ContactResponse patchContact(
            Long id,
            ContactPatchRequest request) {

        Contact contact = contactRepository.findById(id)
                .orElseThrow(() ->
                        new ContactNotFoundException(
                                "Contact not found with id: " + id
                        )
                );

        if (request.firstName() != null) {
            contact.setFirstName(request.firstName());
        }

        if (request.lastName() != null) {
            contact.setLastName(request.lastName());
        }

        if (request.email() != null) {

            if (!contact.getEmail().equalsIgnoreCase(request.email())
                    && contactRepository.existsByEmail(request.email())) {

                throw new DuplicateContactException(
                        "Contact already exists with email: "
                                + request.email()
                );
            }

            contact.setEmail(request.email());
        }

        if (request.phoneNumber() != null) {
            contact.setPhoneNumber(request.phoneNumber());
        }

        if (request.address() != null) {
            contact.setAddress(request.address());
        }

        contact.setUpdatedAt(LocalDateTime.now());

        Contact updatedContact = contactRepository.save(contact);

        return contactMapper.toResponse(updatedContact);
    }

    @Override
    public void deleteContact(Long id) {

        if (!contactRepository.existsById(id)) {
            throw new ContactNotFoundException(
                    "Contact not found with id: " + id
            );
        }

        contactRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ContactResponse> searchContacts(String keyword) {

        return contactRepository
                .findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrEmailContainingIgnoreCaseOrPhoneNumberContaining(
                        keyword,
                        keyword,
                        keyword,
                        keyword
                )
                .stream()
                .map(contactMapper::toResponse)
                .toList();
    }
}