package com.pixnetics.wallet_backend.payment.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Ledger {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long transactionId;
    private long referenceId;
    private long walletId;
    private double amount;
    private String currency;
    private double vat;
    private double vatRate;
    private String type;
}
