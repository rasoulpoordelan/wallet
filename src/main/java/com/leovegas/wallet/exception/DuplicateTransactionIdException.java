package com.leovegas.wallet.exception;

import org.springframework.http.HttpStatus;

public class DuplicateTransactionIdException extends CustomException {

  public DuplicateTransactionIdException() {
    super("duplicate_transaction_id", HttpStatus.BAD_REQUEST);
  }

  public DuplicateTransactionIdException(String msg) {
    super("duplicate_transaction_id", HttpStatus.BAD_REQUEST, msg);
  }

}

