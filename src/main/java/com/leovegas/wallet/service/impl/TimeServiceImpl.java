package com.leovegas.wallet.service.impl;

import com.leovegas.wallet.service.TimeService;
import com.leovegas.wallet.service.WalletService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TimeServiceImpl implements TimeService {


    @Override
    public LocalDateTime now() {
        return LocalDateTime.now();
    }
}
