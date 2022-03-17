package com.example.bgcpromogearreworked.api.products.exceptions;

public class ProductImageSaveFailedException extends RuntimeException {
    public ProductImageSaveFailedException() {
        super("Failed to save product image.");
    }
}
