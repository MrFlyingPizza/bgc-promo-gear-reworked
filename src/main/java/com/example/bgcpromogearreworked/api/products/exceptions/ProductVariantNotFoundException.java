package com.example.bgcpromogearreworked.api.products.exceptions;

public class ProductVariantNotFoundException extends Exception {

    public ProductVariantNotFoundException() {
        super("Product variant not found.");
    }

}
