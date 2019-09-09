package com.leovegas.wallet.repository;

import com.leovegas.wallet.entity.Wallet;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@DataJpaTest
public class WalletRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private WalletRepository walletRepository;

    long playerId = 1;
    double initialAmount = 20;
    double amount = 10;
    double negativeAmount = -10;
    Wallet wallet;
    long walletId;
    LocalDateTime currentDate=LocalDateTime.now();

    @Before
    public void init() {

        wallet = new Wallet(playerId, currentDate, currentDate);
        entityManager.persist(wallet);
        walletRepository.updateBalance(playerId, initialAmount,currentDate);
        entityManager.flush();
        walletId = wallet.getId();
    }


    @Test
    public void whenUpdateBalanceWithPositiveAmount() {
        int change = walletRepository.updateBalance(playerId, amount,currentDate);

        Wallet expectedWallet = walletRepository.getOne(walletId);
        assertEquals(change,  1);
        assertEquals(expectedWallet.getBalance(), initialAmount+amount, 0);
    }

    @Test
    public void whenUpdateBalanceWithNegativeAmount() {
        int change = walletRepository.updateBalance(playerId, negativeAmount,currentDate);

        Wallet cuWallet = walletRepository.getOne(walletId);

        assertEquals(change,  1);
        assertEquals(cuWallet.getBalance(), initialAmount + negativeAmount, 0);
    }

    @Test
    public void whenUpdateBalanceWithPositiveAmountGreaterThanCurrentAmount() {
        int change = walletRepository.updateBalance(playerId, (initialAmount + 5) * -1,currentDate);

        Wallet cuWallet = walletRepository.getOne(walletId);

        assertEquals(change,  0);
        assertEquals(cuWallet.getBalance(), initialAmount , 0);
    }

    @Test
    public void whenCallFindFirstByPlayerId() {

        Wallet wallet=walletRepository.findFirstByPlayerId(playerId);

        assertEquals(wallet.getBalance(),  initialAmount,0);
        assertEquals(wallet.getPlayerId(), playerId);
    }


}
