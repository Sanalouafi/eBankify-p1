package com.example.ebankifyp1.exception;

public class TransactionNotFoundException extends RuntimeException {
    public TransactionNotFoundException(String message) {

        super(message);
    }
}
