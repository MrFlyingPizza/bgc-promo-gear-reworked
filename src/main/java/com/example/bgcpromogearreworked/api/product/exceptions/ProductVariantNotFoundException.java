package com.example.bgcpromogearreworked.api.product.exceptions;

public class ProductVariantNotFoundException extends Exception {
    public ProductVariantNotFoundException() {
        super("Product variant not found.");
    }
}
