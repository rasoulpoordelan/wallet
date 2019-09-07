package com.leovegas.wallet.entity;

import com.leovegas.wallet.exception.InsufficientBalanceException;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "wallet", uniqueConstraints = @UniqueConstraint(columnNames = {"player_id"}, name = "IX_UNIQUE_WALLET_PLAYER"))
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Access(AccessType.PROPERTY)
    @Column(name = "id")
    private long id;

    @Column(name = "player_id")
    private long playerId;

    @Column(name = "balance")
    private double balance = 0;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @Column(name = "update_date")
    private LocalDateTime updateDate;

    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private Set<WalletTransaction> transactions = new HashSet<>();

   /* @Version
    private Integer version;*/

    public Wallet() {
    }

    public Wallet(long playerId, LocalDateTime createDate, LocalDateTime updateDate) {
        this.playerId = playerId;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }

    public void credit(double amount) {
        this.balance += amount;
    }

    public void debit(double amount) {
        this.balance -= amount;
        if (this.balance < 0)
            throw new InsufficientBalanceException();
    }

    public void changeBalance(double amount) {
        this.balance += amount;
        if (this.balance < 0)
            throw new InsufficientBalanceException();
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }

    public Set<WalletTransaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(Set<WalletTransaction> transactions) {
        this.transactions = transactions;
    }
}
