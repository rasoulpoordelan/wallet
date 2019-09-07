package com.leovegas.wallet.exception;

import org.springframework.http.HttpStatus;

public class InsufficientBalanceException extends CustomException {

  public InsufficientBalanceException() {
    super("insufficient_balance", HttpStatus.BAD_REQUEST);
  }

  public InsufficientBalanceException(String msg) {
    super("insufficient_balance", HttpStatus.BAD_REQUEST, msg);
  }

}

