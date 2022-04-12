package ca.bgcengineering.promogearreworked.api.users.exceptions;

public class UserCartItemNotFoundException extends RuntimeException {

    public UserCartItemNotFoundException() {
        super("Cart item not found.");
    }
}
