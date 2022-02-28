package com.example.bgcpromogearreworked.api.categories.category.exceptions;

public class CategoryNotFoundException extends Exception {

    public CategoryNotFoundException() {
        super("Category not found.");
    }

}
