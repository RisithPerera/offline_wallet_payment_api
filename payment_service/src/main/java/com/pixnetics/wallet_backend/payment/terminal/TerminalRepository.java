package com.pixnetics.wallet_backend.payment.terminal;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TerminalRepository extends JpaRepository<Terminal, UUID> {}