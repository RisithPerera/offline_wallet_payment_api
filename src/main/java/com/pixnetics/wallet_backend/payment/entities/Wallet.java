package com.pixnetics.wallet_backend.payment.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long ownerId;
    private String type;
    private String name;
    private double balance;
    private String currency;
    private String status;
}
