package com.ebanking.ebanking.model.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String username){
        super(String.format("USER WITH %S USERNAME WAS NOT FOUND", username));
    }
}
