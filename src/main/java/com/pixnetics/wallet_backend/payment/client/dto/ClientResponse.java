package com.pixnetics.wallet_backend.payment.client.dto;

import java.util.UUID;

public record ClientResponse(
        UUID id,
        String username
) {}
