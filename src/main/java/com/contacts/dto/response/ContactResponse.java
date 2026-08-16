package com.contacts.dto.response;

import java.time.LocalDateTime;

public record ContactResponse(

        Long id,
        String firstName,
        String lastName,
        String email,
        String phoneNumber,
        String address,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}