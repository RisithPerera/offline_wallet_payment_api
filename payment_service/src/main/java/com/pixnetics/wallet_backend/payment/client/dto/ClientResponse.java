package com.pixnetics.wallet_backend.payment.client.dto;

import com.pixnetics.wallet_backend.payment.wallet.dto.WalletResponse;

import java.util.List;
import java.util.UUID;

public record ClientResponse(
        UUID id,
        String username,
        List<WalletResponse> wallets
) {}
