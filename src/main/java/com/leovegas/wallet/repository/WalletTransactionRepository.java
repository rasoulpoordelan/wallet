package com.leovegas.wallet.repository;


import com.leovegas.wallet.entity.Wallet;
import com.leovegas.wallet.entity.WalletTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WalletTransactionRepository extends JpaRepository<WalletTransaction,Long> {

    @Query("SELECT c FROM WalletTransaction c left join fetch c.wallet WHERE c.wallet.playerId = ?1 ORDER BY c.createDate desc ")
    List<WalletTransaction> findByPlayerId(Long Id);

}
