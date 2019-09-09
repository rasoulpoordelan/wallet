package com.leovegas.wallet.service;

import com.leovegas.wallet.entity.Wallet;
import com.leovegas.wallet.entity.WalletTransaction;
import com.leovegas.wallet.exception.InsufficientBalanceException;
import com.leovegas.wallet.repository.WalletRepository;
import com.leovegas.wallet.repository.WalletTransactionRepository;
import com.leovegas.wallet.service.impl.WalletServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;

@RunWith(MockitoJUnitRunner.Silent.class)
public class WalletServiceTest {

    @Mock
    WalletRepository walletRepository;

    @Mock
    WalletTransactionRepository walletTransactionRepository;

    @Mock
    TimeService timeService;

    @InjectMocks
    private WalletService walletService=new WalletServiceImpl();

    long playerId=1;
    double amount=10;
    double bigAmountDebit=500;
    Wallet wallet;
    WalletTransaction walletTransaction;
    String transactionId ="T1";
    LocalDateTime currentDate=LocalDateTime.now();

    long playerIdSec=2;
    Wallet walletSec;


    @Before
    public void init() {

        Mockito.when(timeService.now()).thenReturn(currentDate);

        wallet = new Wallet(playerId,LocalDateTime.now(),currentDate);
        wallet.setId(1);

        walletSec = new Wallet(playerIdSec,LocalDateTime.now(),currentDate);
        wallet.setId(2);

        walletTransaction = new WalletTransaction(wallet,transactionId,amount,currentDate,"");

        Mockito.when(walletRepository.findFirstByPlayerId(playerId)).thenReturn(wallet);
        Mockito.when(walletRepository.save(wallet)).thenReturn(wallet);

        Mockito.when(walletRepository.findFirstByPlayerId(playerIdSec)).thenReturn(walletSec);
        Mockito.when(walletRepository.save(walletSec)).thenReturn(walletSec);

        Mockito.when(walletRepository.updateBalance(playerId,amount,currentDate)).thenReturn(1);
        Mockito.when(walletRepository.updateBalance(playerId,bigAmountDebit,currentDate)).thenReturn(0);

        Mockito.when(walletTransactionRepository.save(walletTransaction)).thenReturn(walletTransaction);

    }

    @Test
    public  void creditTest()
    {
        walletService.credit(playerId,amount,transactionId);
    }


    @Test(expected = InsufficientBalanceException.class)
    public  void debitInsufficientBalanceException()
    {
        walletService.debit(playerId,bigAmountDebit,transactionId);
    }

}
