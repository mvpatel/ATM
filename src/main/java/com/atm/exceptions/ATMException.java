package com.atm.exceptions;

public class ATMException extends RuntimeException{
    public ATMException(String errorMessage) {
        super(errorMessage);
    }
}
