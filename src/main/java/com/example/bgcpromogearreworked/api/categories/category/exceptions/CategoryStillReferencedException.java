package com.example.bgcpromogearreworked.api.categories.category.exceptions;

public class CategoryStillReferencedException extends RuntimeException {

    public CategoryStillReferencedException() {
        super("Category is still referenced.");
    }
}
