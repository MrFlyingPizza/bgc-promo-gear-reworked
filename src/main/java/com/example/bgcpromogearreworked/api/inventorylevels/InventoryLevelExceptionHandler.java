package com.example.bgcpromogearreworked.api.inventorylevels;

import com.example.bgcpromogearreworked.api.inventorylevels.exceptions.InventoryLevelNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class InventoryLevelExceptionHandler {

    @ExceptionHandler(InventoryLevelNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private String handleInventoryLevelNotFound(InventoryLevelNotFoundException exception) {
        return "Inventory level could not be found.";
    }

}
