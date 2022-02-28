package com.example.bgcpromogearreworked.api.products;

import com.example.bgcpromogearreworked.api.products.exceptions.ProductNotFoundException;
import com.example.bgcpromogearreworked.api.products.exceptions.ProductVariantNotFoundException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@RestControllerAdvice
public class ProductControllerAdvice {

    @ExceptionHandler(ProductNotFoundException.class)
    private String handleProductNotFound(ProductNotFoundException exception) {
        return "Product could not be found.";
    }

    @ExceptionHandler(ProductVariantNotFoundException.class)
    private String handleProductVariantNotFound(ProductVariantNotFoundException exception) {
        return "Product variant could not be found.";
    }
}
