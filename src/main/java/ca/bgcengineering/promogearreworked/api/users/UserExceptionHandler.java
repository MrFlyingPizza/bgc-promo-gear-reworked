package ca.bgcengineering.promogearreworked.api.users;

import ca.bgcengineering.promogearreworked.api.users.exceptions.UserAuthenticationClaimInvalidException;
import ca.bgcengineering.promogearreworked.api.users.exceptions.UserCartItemNotFoundException;
import ca.bgcengineering.promogearreworked.api.users.exceptions.UserNotAuthenticatedException;
import ca.bgcengineering.promogearreworked.api.users.exceptions.UserNotFoundException;
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

    @ExceptionHandler(UserAuthenticationClaimInvalidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private String handleUserAuthClaimInvalid(UserAuthenticationClaimInvalidException exception) {
        return "The authentication claims of the current user is invalid.";
    }

    @ExceptionHandler(UserCartItemNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private String handleUserCartItemNotFound(UserCartItemNotFoundException exception) {
        return "User cart item could not be found.";
    }

}
