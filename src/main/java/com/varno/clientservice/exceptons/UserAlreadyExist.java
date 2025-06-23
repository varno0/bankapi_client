package com.varno.clientservice.exceptons;

public class UserAlreadyExist extends RuntimeException {
    public UserAlreadyExist(String message) {
        super(message);
    }
}
