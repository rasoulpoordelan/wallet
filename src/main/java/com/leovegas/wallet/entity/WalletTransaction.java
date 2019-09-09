package com.leovegas.wallet.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "wallet_transaction", uniqueConstraints = @UniqueConstraint(columnNames = {"transaction_id"}, name = "IX_UNIQUE_WALLET_TRANSACTION_TRANSACTION_ID"))
public class WalletTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Access(AccessType.PROPERTY)
    @Column(name = "id")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wallet_id", foreignKey = @ForeignKey(name = "FK_WALLET_WALLET_TRANSACTION"))
    private Wallet wallet;


    @Column(name = "transaction_id", length = 200)
    private String transactionId;

    @Column(name = "amount")
    private double amount;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @Column(name = "create_by",length = 200)
    private String CreatedBy;

    public WalletTransaction() {
    }

    public WalletTransaction(Wallet wallet, String transactionId, double amount, LocalDateTime createDate, String createdBy) {
        this.wallet = wallet;
        this.transactionId = transactionId;
        this.amount = amount;
        this.createDate = createDate;
        CreatedBy = createdBy;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public String getCreatedBy() {
        return CreatedBy;
    }

    public void setCreatedBy(String createdBy) {
        CreatedBy = createdBy;
    }
}
