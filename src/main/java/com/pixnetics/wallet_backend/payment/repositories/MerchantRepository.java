package com.pixnetics.wallet_backend.payment.repositories;

import com.pixnetics.wallet_backend.payment.entities.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MerchantRepository extends JpaRepository<Merchant, UUID> {}