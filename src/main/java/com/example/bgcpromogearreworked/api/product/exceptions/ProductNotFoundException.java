package com.example.bgcpromogearreworked.api.product.exceptions;

public class ProductNotFoundException extends Exception {

    public ProductNotFoundException() {
        super("Product not found.");
    }
}
