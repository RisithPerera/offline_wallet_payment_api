package com.pixnetics.wallet_backend.payment.terminal.exception;

import java.util.UUID;

public class TerminalNotFoundException extends RuntimeException {

    public TerminalNotFoundException(UUID id) {
        super("Terminal with id " + id + " was not found");
    }
}
