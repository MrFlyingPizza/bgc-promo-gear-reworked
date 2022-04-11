package com.example.bgcpromogearreworked.api.users.exceptions;

public class UserNotAuthenticatedException extends RuntimeException {
    public UserNotAuthenticatedException() {
        super("User is not authenticated.");
    }
}
