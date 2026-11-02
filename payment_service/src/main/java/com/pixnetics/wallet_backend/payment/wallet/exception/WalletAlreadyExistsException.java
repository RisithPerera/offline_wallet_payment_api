package com.pixnetics.wallet_backend.payment.wallet.exception;

public class WalletAlreadyExistsException extends RuntimeException {

    public WalletAlreadyExistsException(String username) {
        super("Wallet is already exits for: " + username);
    }
}
