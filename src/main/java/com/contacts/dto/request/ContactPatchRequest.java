package com.contacts.dto.request;

public record ContactPatchRequest(
        String firstName,
        String lastName,
        String email,
        String phoneNumber,
        String address
) {
}