package com.leovegas.wallet.service;

import com.leovegas.wallet.model.WalletResponseModel;
import com.leovegas.wallet.model.WalletTransactionResponseModel;

import java.util.List;

public interface WalletService {


    void credit(long playerId, double amount, String transactionId);

    void debit(long playerId, double amount, String transactionId);

    List<WalletTransactionResponseModel> playerTransactions(long playerId);

    WalletResponseModel getPlayerWallet(long playerId);
}
