package com.leovegas.wallet.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends CustomException {

  public NotFoundException() {
    super("not_found", HttpStatus.NOT_FOUND);
  }

  public NotFoundException(String msg) {
    super("not_found", HttpStatus.NOT_FOUND, msg);
  }

}

