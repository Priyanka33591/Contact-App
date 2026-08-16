package com.contacts.repository;

import com.contacts.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

    boolean existsByEmail(String email);

    List<Contact> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrEmailContainingIgnoreCaseOrPhoneNumberContaining(
            String firstName,
            String lastName,
            String email,
            String phoneNumber
    );
}