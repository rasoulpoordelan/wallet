package com.leovegas.wallet.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseHelper {

  private ResponseHelper() {
  }

  public static <T> ResponseEntity response(T t) {
    return response(t, "ok", HttpStatus.OK);
  }

  public static <T> ResponseEntity response(T t, HttpStatus status) {
    return response(t, "ok", status);
  }

  public static <T> ResponseEntity response(T t, String msg, HttpStatus status) {
    return new ResponseEntity(new ResponseStructure(t, msg), status);
  }
}
