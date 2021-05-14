package com.atm.exceptions;

public class AccountException extends RuntimeException{
    public AccountException(String errorMessage) {
        super(errorMessage);
    }
}
