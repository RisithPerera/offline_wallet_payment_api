package com.pixnetics.wallet_backend.payment.client.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ClientCreationRequest(
        @NotBlank
        @Size(min = 5, max = 20)
        String username,

        @NotBlank
        @Size(min = 10, max = 20)
        String password,

        @NotNull
        String publicKey
) {}
