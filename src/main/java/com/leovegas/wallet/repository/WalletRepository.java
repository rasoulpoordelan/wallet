package com.leovegas.wallet.repository;


import com.leovegas.wallet.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.LockModeType;
import java.time.LocalDateTime;

public interface WalletRepository extends JpaRepository<Wallet, Long> {

    @Lock(LockModeType.NONE)
    @Query("SELECT w FROM Wallet w WHERE w.playerId = ?1")
    Wallet findFirstByPlayerId(Long Id);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE Wallet w SET w.balance =  w.balance + :amount , w.updateDate = :updateDate WHERE w.playerId = :playerId and w.balance + :amount  > 0 ")
    int updateBalance(@Param("playerId") long playerId, @Param("amount") double amount, @Param("updateDate") LocalDateTime updateDate);

}
