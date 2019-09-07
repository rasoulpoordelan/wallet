package com.leovegas.wallet.model;

import java.time.LocalDateTime;

public class WalletResponseModel {

    private long playerId;

    private double balance;

    private LocalDateTime updateDate;

    public WalletResponseModel(long playerId, double balance, LocalDateTime updateDate) {
        this.playerId = playerId;
        this.balance = balance;
        this.updateDate = updateDate;
    }

    public long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(long playerId) {
        this.playerId = playerId;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }
}
