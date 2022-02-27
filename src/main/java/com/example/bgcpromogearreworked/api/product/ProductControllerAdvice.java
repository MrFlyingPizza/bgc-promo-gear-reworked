package com.example.bgcpromogearreworked.api.product;

import com.example.bgcpromogearreworked.api.product.exceptions.ProductNotFoundException;
import com.example.bgcpromogearreworked.api.product.exceptions.ProductVariantNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
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
