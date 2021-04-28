package com.ebanking.ebanking.model.exceptions;

public class NotSupportedTransferException extends RuntimeException {
    public NotSupportedTransferException(int number){
        super(String.format("Account with number %d does not have enough money to transfer", number));
    }
}
