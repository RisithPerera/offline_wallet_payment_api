package com.pixnetics.wallet_backend.payment.user.exception;

public class UsernameAlreadyExistsException extends RuntimeException {

    public UsernameAlreadyExistsException(String username) {
        super("Username is already taken: " + username);
    }
}
