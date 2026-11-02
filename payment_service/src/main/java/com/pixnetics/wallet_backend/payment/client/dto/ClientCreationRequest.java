package com.pixnetics.wallet_backend.payment.client.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Schema(description = "Request to register a new client")
public record ClientCreationRequest(
        @Schema(
                description = "Client's login username",
                example = "user123"
        )
        @NotBlank
        @Size(min = 5, max = 20)
        String username,

        @NotBlank
        @Size(min = 10, max = 20)
        String password,

        @NotNull
        String publicKey
) {}
