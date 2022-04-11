package ca.bgcengineering.promogearreworked.api.orders.exceptions;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException() {
        super("Order not found.");
    }
}
