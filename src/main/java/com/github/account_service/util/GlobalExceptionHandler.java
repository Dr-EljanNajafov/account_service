package com.github.account_service.util;

import com.github.account_service.util.exceptionhandler.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleInvalidFieldException(EntityNotFoundException ex) {
        log.error("Exception caused: {}. \n" +
                "Stacktrace: {}", ex.getMessage(), ex.getStackTrace());

        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
