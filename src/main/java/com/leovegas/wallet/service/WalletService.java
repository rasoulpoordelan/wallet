package com.leovegas.wallet.service;

import com.leovegas.wallet.exception.DuplicateTransactionIdException;
import com.leovegas.wallet.model.WalletResponseModel;
import com.leovegas.wallet.model.WalletTransactionResponseModel;
import org.springframework.retry.annotation.Retryable;

import java.util.List;

public interface WalletService {


    WalletResponseModel credit(long playerId, double amount, String transactionId);

   /* @Retryable(value = {DuplicateTransactionIdException.class}, maxAttempts = 2)*/
    WalletResponseModel debit(long playerId, double amount, String transactionId);

    List<WalletTransactionResponseModel> playerTransactions(long playerId);

    WalletResponseModel getPlayerWallet(long playerId);
}
