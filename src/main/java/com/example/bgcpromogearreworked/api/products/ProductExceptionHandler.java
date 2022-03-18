package com.example.bgcpromogearreworked.api.products;

import com.example.bgcpromogearreworked.api.products.exceptions.ProductImageNotFoundException;
import com.example.bgcpromogearreworked.api.products.exceptions.ProductImageSaveFailedException;
import com.example.bgcpromogearreworked.api.products.exceptions.ProductNotFoundException;
import com.example.bgcpromogearreworked.api.products.exceptions.ProductVariantNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@RestControllerAdvice
public class ProductExceptionHandler {

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

    @ExceptionHandler(ProductImageNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private String handleProductImageNotFound(ProductImageNotFoundException exception) {
        return "Product image could not be found.";
    }

    @ExceptionHandler(ProductImageSaveFailedException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    private String handleProductImageSaveFailed(ProductImageSaveFailedException exception) {
        return "Server failed to save the image.";
    }

}
