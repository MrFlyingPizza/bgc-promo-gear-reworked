package ca.bgcengineering.promogearreworked.api.users.exceptions;

public class UserAuthenticationClaimInvalidException extends RuntimeException {
    public UserAuthenticationClaimInvalidException() {
        super("User authentication claims invalid.");
    }

    public UserAuthenticationClaimInvalidException(String message) {
        super(message);
    }
}
