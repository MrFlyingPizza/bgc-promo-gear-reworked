package com.example.bgcpromogearreworked.api.inventorylevels.exceptions;

public class GlobalInventoryLevelNotFound extends RuntimeException {

    public GlobalInventoryLevelNotFound() {
        super("Global inventory level not found.");
    }
}
