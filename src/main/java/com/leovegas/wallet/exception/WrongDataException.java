package com.leovegas.wallet.exception;

import org.springframework.http.HttpStatus;

public class WrongDataException extends CustomException {

  public WrongDataException() {
    super("wrong_data", HttpStatus.BAD_REQUEST);
  }

  public WrongDataException(String msg) {
    super("wrong_data", HttpStatus.BAD_REQUEST, msg);
  }

}

