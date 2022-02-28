package com.example.bgcpromogearreworked.api.products.exceptions;

public class ProductNotFoundException extends Exception {

    public ProductNotFoundException() {
        super("Product not found.");
    }
}
