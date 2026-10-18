package com.pixnetics.wallet_backend.payment.repositories;

import com.pixnetics.wallet_backend.payment.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {}