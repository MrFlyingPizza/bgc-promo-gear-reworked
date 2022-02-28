package com.example.bgcpromogearreworked.api.categories;

import com.example.bgcpromogearreworked.api.categories.category.exceptions.CategoryNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CategoryControllerAdvice {

    @ExceptionHandler(CategoryNotFoundException.class)
    private String handleCategoryNotFound(CategoryNotFoundException exception) {
        return "Category not found.";
    }

}
