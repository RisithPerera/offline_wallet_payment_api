package com.pixnetics.wallet_backend.payment.wallet.dto;

import java.util.UUID;

public record WalletResponse(
        UUID id,
        String name,
        String type,
        double balance,
        String currency
) {}
