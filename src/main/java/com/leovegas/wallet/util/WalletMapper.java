package com.leovegas.wallet.util;

import com.leovegas.wallet.entity.Wallet;
import com.leovegas.wallet.entity.WalletTransaction;
import com.leovegas.wallet.model.WalletResponseModel;
import com.leovegas.wallet.model.WalletTransactionResponseModel;
import org.springframework.stereotype.Component;

@Component
public class WalletMapper {

    public static WalletResponseModel walletToWalletResponseModel(Wallet model)
    {
        return new WalletResponseModel(model.getPlayerId(),model.getBalance(),model.getUpdateDate());
    }

    public static WalletTransactionResponseModel walletTransactionToWalletTransactionReponseModel( WalletTransaction model)
    {
        return new WalletTransactionResponseModel(model.getWallet().getPlayerId(),model.getTransactionId(),model.getAmount(),model.getBalance(),model.getCreateDate());
    }
}
