package com.ebanking.ebanking.model.exceptions;

public class PasswordsDoNotMatchException extends RuntimeException {

    public PasswordsDoNotMatchException(){
        super("Passwords do not match. Try again!");
    }
}
