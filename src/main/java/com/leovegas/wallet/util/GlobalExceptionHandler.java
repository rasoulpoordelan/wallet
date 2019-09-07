package com.leovegas.wallet.util;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;

import com.leovegas.wallet.exception.CustomException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @Value("${api.app.log.show-reason:true}")
    private boolean showReason;

    @ExceptionHandler(CustomException.class)
    public ResponseEntity handleCustomException(CustomException ex, HttpServletRequest request) {
        logger.warn(ex.getMsg());
        return ResponseHelper.response(ex.getData(), ex.getMsg(), ex.getStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity methodArgumentNotValidException(MethodArgumentNotValidException ex,
                                                          HttpServletRequest request) {
        List<String> items1 = new ArrayList<>();

        ex.getBindingResult().getFieldErrors()
                .stream()
                .collect(Collectors.groupingBy(i -> i.getField()))
                .forEach((s, fieldErrors) -> items1.add(String.format("%s : %s", s,
                        String.join(",", fieldErrors.stream().map(i -> i.getDefaultMessage()).collect(
                                Collectors.toList())))));
        logger.warn(ex.getMessage());
        return ResponseHelper.response(items1, "wrong_model", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity invalidJsonException(Exception ex, HttpServletRequest request) {
        logger.warn(ex.getMessage());
        return ResponseHelper.response("Please check your request body structure!", "invalid_json",
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleException(Exception ex, HttpServletRequest request) {
        logger.error(ex.getMessage());
        if (showReason) {
            return ResponseHelper
                    .response(ex.getMessage(), "server_error", HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            return ResponseHelper
                    .response("Please contact to LeoVegas server department!", "server_error",
                            HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}

