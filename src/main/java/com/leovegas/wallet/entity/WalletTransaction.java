package com.leovegas.wallet.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "wallet_transaction", uniqueConstraints = @UniqueConstraint(columnNames = {"transaction_id"},name = "IX_UNIQUE_WALLET_TRANSACTION_TRANSACTION_ID"))
public class WalletTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Access(AccessType.PROPERTY)
    @Column(name = "id")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wallet_id", foreignKey = @ForeignKey(name = "FK_WALLET_WALLET_TRANSACTION"))
    private Wallet wallet;


    @Column(name = "transaction_id",length = 200)
    private String transactionId;

    @Column(name = "amount")
    private double amount;

    @Column(name = "balance")
    private  double balance;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    public WalletTransaction() {
    }

    public WalletTransaction(Wallet wallet, String transactionId, double amount, double balance, LocalDateTime createDate) {
        this.wallet = wallet;
        this.transactionId = transactionId;
        this.amount = amount;
        this.balance = balance;
        this.createDate = createDate;
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

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }
}
