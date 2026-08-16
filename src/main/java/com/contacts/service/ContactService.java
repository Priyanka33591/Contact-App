package com.contacts.service;

import com.contacts.dto.request.ContactPatchRequest;
import com.contacts.dto.request.ContactRequest;
import com.contacts.dto.response.ContactResponse;

import java.util.List;

public interface ContactService {

    ContactResponse createContact(ContactRequest request);

    List<ContactResponse> getAllContacts();

    ContactResponse getContactById(Long id);

    ContactResponse updateContact(Long id, ContactRequest request);

    ContactResponse patchContact(Long id, ContactPatchRequest request);

    void deleteContact(Long id);

    List<ContactResponse> searchContacts(String keyword);
}