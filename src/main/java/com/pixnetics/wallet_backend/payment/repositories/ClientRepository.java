package com.pixnetics.wallet_backend.payment.repositories;

import com.pixnetics.wallet_backend.payment.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClientRepository extends JpaRepository<Client, UUID> {}