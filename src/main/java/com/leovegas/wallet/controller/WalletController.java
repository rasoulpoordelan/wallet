package com.leovegas.wallet.controller;

import com.leovegas.wallet.model.WalletRequestModel;
import com.leovegas.wallet.model.WalletResponseModel;
import com.leovegas.wallet.model.WalletTransactionResponseModel;
import com.leovegas.wallet.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("wallet")
public class WalletController {

    @Autowired
    private WalletService walletService;

    @RequestMapping(method = RequestMethod.POST, value = "credit")
    public boolean add(@RequestBody @Valid WalletRequestModel model) {
         walletService.credit(model.getPlayerId(), model.getAmount(), model.getTransactionId());
         return true;
    }


    @RequestMapping(method = RequestMethod.POST, value = "debit")
    public boolean debit(@RequestBody @Valid WalletRequestModel model) {
         walletService.debit(model.getPlayerId(), model.getAmount(), model.getTransactionId());
         return true;
    }

    @RequestMapping(method = RequestMethod.GET, value = "balance/{playerId}")
    public WalletResponseModel getBalance(@PathVariable("playerId") long playerId) {
        return walletService.getPlayerWallet(playerId);
    }


    @RequestMapping(method = RequestMethod.GET, value = "playerTransaction/{playerId}")
    public List<WalletTransactionResponseModel> getAllTransaction(@PathVariable("playerId") long playerId) {
        return walletService.playerTransactions(playerId);
    }

}
