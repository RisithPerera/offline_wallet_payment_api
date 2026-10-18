package com.pixnetics.wallet_backend.payment.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String type;
    private long userId;
    private long fromWalletId;
    private long toWalletId;
    private double amount;
    private String currency;
    private double vatRate;
    private String status;
    private String failureReason;
}
