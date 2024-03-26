package com.echobot.echobot.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionAdvice {
    @ExceptionHandler(StrategyHandleException.class)
    public ResponseEntity<ExceptionData> handleFailedSavingCardException(StrategyHandleException exception) {
        ExceptionData fileEntityIssue = new ExceptionData();
        fileEntityIssue.setInfo(exception.getMessage());
        return new ResponseEntity<>(fileEntityIssue, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(EventParsingException.class)
    public ResponseEntity<ExceptionData> handleEventParsingException(EventParsingException exception) {
        ExceptionData fileEntityIssue = new ExceptionData();
        fileEntityIssue.setInfo(exception.getMessage());
        return new ResponseEntity<>(fileEntityIssue, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
