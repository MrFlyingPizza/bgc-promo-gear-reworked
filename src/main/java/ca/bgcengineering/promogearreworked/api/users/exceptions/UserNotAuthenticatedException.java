package ca.bgcengineering.promogearreworked.api.users.exceptions;

public class UserNotAuthenticatedException extends RuntimeException {
    public UserNotAuthenticatedException() {
        super("User is not authenticated.");
    }
}
