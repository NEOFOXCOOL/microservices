package com.marri.customer;

public record CustomerRegisterRequest(
        String firstName,
        String lastName,
        String email
) {
}
