package com.leovegas.wallet.repository;


import com.leovegas.wallet.entity.WalletTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WalletTransactionRepository extends JpaRepository<WalletTransaction,Long> {

    @Query("SELECT w FROM WalletTransaction w left join fetch w.wallet WHERE w.wallet.playerId = ?1 ORDER BY w.createDate desc ")
    List<WalletTransaction> findAllByPlayerIdOrderByDesc(Long Id);

}
