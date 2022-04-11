package ca.bgcengineering.promogearreworked.api.orders.exceptions;

public class NoCartItemException extends RuntimeException {

    public NoCartItemException() {
        super("No cart item.");
    }
}
