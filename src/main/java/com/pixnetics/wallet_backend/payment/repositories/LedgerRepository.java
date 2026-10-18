package com.pixnetics.wallet_backend.payment.repositories;

import com.pixnetics.wallet_backend.payment.entities.Ledger;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LedgerRepository extends JpaRepository<Ledger, UUID> {}