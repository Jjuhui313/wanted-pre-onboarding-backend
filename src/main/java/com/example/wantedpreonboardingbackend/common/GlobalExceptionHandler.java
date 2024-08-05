package com.example.wantedpreonboardingbackend.common;

import com.example.wantedpreonboardingbackend.common.msg.ExceptionMsg;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = {CustomException.class})
    protected ResponseEntity<ExceptionMsg> handleCustomException(CustomException e) {
        log.error("handleCustomException throw CustomException : {}", e.getExceptionMsg());
        return new ResponseEntity<>(e.getExceptionMsg(), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler({MissingRequestHeaderException.class})
    public ResponseEntity<String> missingRequestHeaderException(MissingRequestHeaderException ex) {
        log.error("MissingRequestHeaderException: {}", ex.getMessage());
        return new ResponseEntity<>("Missing Request Header", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<String> handleAll(final Exception ex) {
        log.error("Exception: {}", ex.getMessage());
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {IOException.class})
    public ResponseEntity<String> handleIOException(IOException ex) {
        log.error("IOException: {}", ex.getMessage());
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
