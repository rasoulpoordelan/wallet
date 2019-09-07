package com.leovegas.wallet.model;

import java.time.LocalDateTime;

public class WalletTransactionResponseModel {

    private long playerId;

    private String transactionId;

    private double amount;

    private  double balance;

    private LocalDateTime createDate;

    public WalletTransactionResponseModel(long playerId, String transactionId, double amount, double balance, LocalDateTime createDate) {
        this.playerId = playerId;
        this.transactionId = transactionId;
        this.amount = amount;
        this.balance = balance;
        this.createDate = createDate;
    }

    public long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(long playerId) {
        this.playerId = playerId;
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
