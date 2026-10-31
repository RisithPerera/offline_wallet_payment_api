package com.pixnetics.wallet_backend.payment.wallet;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface WalletRepository extends JpaRepository<Wallet, UUID> {
    List<Wallet> findAllByUser_Id(UUID userId);

    boolean existsByUser_Id(UUID userId);
}