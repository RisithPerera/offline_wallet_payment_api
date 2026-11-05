package com.pixnetics.wallet_backend.payment.client.dto;

import com.pixnetics.wallet_backend.payment.wallet.dto.WalletResponse;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;
import java.util.UUID;


public record ClientResponse(

        @Schema(example = "550e8400-e29b-41d4-a716-446655440000")
        UUID id,

        @Schema(example = "user123")
        String username,

        List<WalletResponse> wallets
) {}
