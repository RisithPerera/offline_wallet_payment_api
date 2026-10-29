package com.pixnetics.wallet_backend.payment.merchant.exception;

import java.util.UUID;

public class MerchantNotFoundException extends RuntimeException {

    public MerchantNotFoundException(UUID id) {
        super("Merchant with id " + id + " was not found");
    }
}
