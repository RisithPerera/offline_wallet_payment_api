package com.pixnetics.wallet_backend.payment.terminal.dto;

import java.util.UUID;

public record TerminalResponse(
        UUID id,
        String username
) {}
