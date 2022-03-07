package com.example.bgcpromogearreworked.api.products;

import com.example.bgcpromogearreworked.api.products.exceptions.ProductNotFoundException;
import com.example.bgcpromogearreworked.api.products.exceptions.ProductVariantNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@RestControllerAdvice
public class ProductControllerAdvice {

    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private String handleProductNotFound(ProductNotFoundException exception) {
        return "Product could not be found.";
    }

    @ExceptionHandler(ProductVariantNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private String handleProductVariantNotFound(ProductVariantNotFoundException exception) {
        return "Product variant could not be found.";
    }
}
