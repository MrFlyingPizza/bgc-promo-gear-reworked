package com.example.bgcpromogearreworked.api.carts.exceptions;

public class CartItemNotFoundException extends RuntimeException {

    public CartItemNotFoundException() {
        super("Cart item not found.");
    }
}
