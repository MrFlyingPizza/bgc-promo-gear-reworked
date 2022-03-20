package com.example.bgcpromogearreworked.api.products.exceptions;

public class ProductVariantNotFoundException extends RuntimeException {

    public ProductVariantNotFoundException() {
        super("Product variant not found.");
    }

}
