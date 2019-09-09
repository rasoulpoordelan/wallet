package com.leovegas.wallet.service.impl;

import com.leovegas.wallet.entity.Wallet;
import com.leovegas.wallet.entity.WalletTransaction;
import com.leovegas.wallet.exception.DuplicateTransactionIdException;
import com.leovegas.wallet.exception.InsufficientBalanceException;
import com.leovegas.wallet.exception.WrongDataException;
import com.leovegas.wallet.model.WalletResponseModel;
import com.leovegas.wallet.model.WalletTransactionResponseModel;
import com.leovegas.wallet.repository.WalletRepository;
import com.leovegas.wallet.repository.WalletTransactionRepository;
import com.leovegas.wallet.service.TimeService;
import com.leovegas.wallet.service.WalletService;
import com.leovegas.wallet.util.WalletMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WalletServiceImpl implements WalletService {

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private WalletTransactionRepository walletTransactionRepository;

    @Autowired
    private TimeService timeService;

    @Override
    @Transactional
    public void credit(long playerId, double amount, String transactionId) {
        creditDebitAction(playerId, amount, transactionId);
    }

    @Override
    @Transactional
    public void debit(long playerId, double amount, String transactionId) {
        creditDebitAction(playerId, amount * -1, transactionId);
    }

    private void creditDebitAction(long playerId, double amount, String transactionId) {

        LocalDateTime currentDate = timeService.now();

        Wallet wallet = walletRepository.findFirstByPlayerId(playerId);
        if (wallet == null) {
            wallet = new Wallet(playerId, currentDate, currentDate);
            walletRepository.save(wallet);
        }

        int change = walletRepository.updateBalance(playerId, amount, currentDate);
        if (change == 0)
            throw new InsufficientBalanceException();

        try {
            walletTransactionRepository.save(new WalletTransaction(wallet, transactionId, amount, currentDate, ""));
        } catch (DataIntegrityViolationException ex) {
            throw new DuplicateTransactionIdException();
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<WalletTransactionResponseModel> playerTransactions(long playerId) {

        return walletTransactionRepository.findAllByPlayerIdOrderByDesc(playerId).stream()
                .map(i -> WalletMapper.walletTransactionToWalletTransactionReponseModel(i))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public WalletResponseModel getPlayerWallet(long playerId) {
        Wallet wallet = walletRepository.findFirstByPlayerId(playerId);

        if (wallet == null)
            throw new WrongDataException();

        return WalletMapper.walletToWalletResponseModel(wallet);

    }


}
