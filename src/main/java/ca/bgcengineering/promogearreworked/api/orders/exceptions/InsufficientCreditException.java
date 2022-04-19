package ca.bgcengineering.promogearreworked.api.orders.exceptions;

public class InsufficientCreditException extends RuntimeException {
    public InsufficientCreditException() {
        super("Insufficient credit.");
    }
}
