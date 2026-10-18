package com.pixnetics.wallet_backend.payment.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Data
@Entity
@Table(name = "ledger")
public class Ledger {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "created_at", nullable = false, insertable = false, updatable = false)
    private Instant createdAt;

    @Column(name = "trans_id", nullable = false)
    private UUID transId;

    @Column(name = "ref_id")
    private UUID refId;

    @Column(name = "wallet_id", nullable = false)
    private UUID walletId;

    @Column(name = "amount", nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;

    @Column(name = "vat", nullable = false, precision = 10, scale = 2)
    private BigDecimal vat = BigDecimal.ZERO;

    @Column(name = "vat_rate", nullable = false, precision = 4, scale = 2)
    private BigDecimal vatRate = BigDecimal.ZERO;

    @Column(name = "currency", nullable = false)
    private String currency = "EUR";

    @Column(name = "type", nullable = false)
    private String type;
}
