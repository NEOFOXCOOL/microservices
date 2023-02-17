package com.marri.client.notification;

public record NotificationRequest(
        Long toCustomerId,
        String toCustomerEmail,
        String message
) { }
