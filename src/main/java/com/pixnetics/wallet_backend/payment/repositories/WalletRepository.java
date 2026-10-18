package com.pixnetics.wallet_backend.payment.repositories;

import com.pixnetics.wallet_backend.payment.entities.Client;
import com.pixnetics.wallet_backend.payment.entities.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface WalletRepository extends JpaRepository<Wallet, UUID> {}