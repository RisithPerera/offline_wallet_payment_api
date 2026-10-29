package com.pixnetics.wallet_backend.payment.merchant.dto;

import java.util.UUID;

public record MerchantResponse(
        UUID id,
        String username
) {}
