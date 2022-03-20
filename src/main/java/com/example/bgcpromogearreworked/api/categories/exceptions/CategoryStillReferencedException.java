package com.example.bgcpromogearreworked.api.categories.exceptions;

public class CategoryStillReferencedException extends RuntimeException {

    public CategoryStillReferencedException() {
        super("Category is still referenced.");
    }
}
