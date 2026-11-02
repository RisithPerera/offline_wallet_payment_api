package com.pixnetics.wallet_backend.payment.wallet.dto;

public record WalletCreationRequest(
        String type,
        String name,
        String currency
) {}
