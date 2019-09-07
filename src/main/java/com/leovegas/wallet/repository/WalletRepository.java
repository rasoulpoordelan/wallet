package com.leovegas.wallet.repository;


import com.leovegas.wallet.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.LockModeType;

public interface WalletRepository extends JpaRepository<Wallet, Long> {

    @Lock(LockModeType.NONE)
    @Query("SELECT c FROM Wallet c WHERE c.playerId = ?1")
    Wallet findFirstByPlayerId(Long Id);

    /*@Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Company c WHERE c.name = :companyName")
    boolean existsByName(@Param("playerId") long playerId);
*/
    @Modifying
    @Query("UPDATE Wallet c SET c.balance =  c.balance + :amount WHERE c.playerId = :playerId and c.balance + :amount  > 0 ")
    int updateBalance(@Param("playerId") long playerId,@Param("amount") double amount);

}
