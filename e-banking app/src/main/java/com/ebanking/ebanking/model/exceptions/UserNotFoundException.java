package com.ebanking.ebanking.model.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String username){
        super(String.format("User with %s username was not found", username));
    }
}
