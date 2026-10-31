package com.pixnetics.wallet_backend.payment.terminal.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record TerminalCreationRequest(
        @NotBlank
        @Size(max = 100)
        String name,

        @NotBlank
        @Size(max = 255)
        String certificateSerialNumber
) {}
