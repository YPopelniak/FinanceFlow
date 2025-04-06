package com.cydeo.exception;

public class BalanceNotSufficientException extends Throwable{
    public BalanceNotSufficientException(String message) {
        super(message);
    }
}
