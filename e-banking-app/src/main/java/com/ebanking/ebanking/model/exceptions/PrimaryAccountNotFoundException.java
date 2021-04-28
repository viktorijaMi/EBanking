package com.ebanking.ebanking.model.exceptions;

public class PrimaryAccountNotFoundException extends RuntimeException {

    public PrimaryAccountNotFoundException(Long id){
        super(String.format("Account with id %d was not found", id));
    }
}
