package com.echobot.echobot.exceptions;

public class ServiceException extends RuntimeException {
    private int statusCode;
    public ServiceException (String message, String e) {
        super(message);
        this.statusCode = statusCode;
    }
}
