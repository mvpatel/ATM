package com.atm.exceptions;

public class FundException extends RuntimeException{
    public FundException(String errorMessage) {
        super(errorMessage);
    }
}
