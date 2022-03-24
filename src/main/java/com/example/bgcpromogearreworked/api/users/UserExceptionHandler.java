package com.example.bgcpromogearreworked.api.users;

import com.example.bgcpromogearreworked.api.users.exceptions.UserNotFoundException;
import com.example.bgcpromogearreworked.api.users.exceptions.UserNotAuthenticatedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private String handleUserNotFound(UserNotFoundException exception) {
        return "User could not be found.";
    }

    @ExceptionHandler(UserNotAuthenticatedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private String handleUserNotAuthenticated(UserNotAuthenticatedException exception) {
        return "Current user is not authenticated and cannot be identified to be used in requests.";
    }

}
