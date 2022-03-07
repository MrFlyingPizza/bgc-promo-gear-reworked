package com.example.bgcpromogearreworked.api.categories;

import com.example.bgcpromogearreworked.api.categories.category.exceptions.CategoryNotFoundException;
import com.example.bgcpromogearreworked.api.categories.category.exceptions.CategoryStillReferencedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CategoryControllerAdvice {

    @ExceptionHandler(CategoryNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private String handleCategoryNotFound(CategoryNotFoundException exception) {
        return "Category not found.";
    }

    @ExceptionHandler(CategoryStillReferencedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private String handleCategoryStillReferenced(CategoryStillReferencedException exception) {
        return "Category is still used by at least one product and cannot be removed.";
    }

}
