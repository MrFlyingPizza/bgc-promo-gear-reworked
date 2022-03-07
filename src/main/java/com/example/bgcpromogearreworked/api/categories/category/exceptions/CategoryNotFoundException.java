package com.example.bgcpromogearreworked.api.categories.category.exceptions;

public class CategoryNotFoundException extends RuntimeException {

    public CategoryNotFoundException() {
        super("Category not found.");
    }

}
