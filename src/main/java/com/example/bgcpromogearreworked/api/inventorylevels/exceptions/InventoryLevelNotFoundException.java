package com.example.bgcpromogearreworked.api.inventorylevels.exceptions;

public class InventoryLevelNotFoundException extends RuntimeException {

    public InventoryLevelNotFoundException() {
        super("Inventory level not found.");
    }
}
