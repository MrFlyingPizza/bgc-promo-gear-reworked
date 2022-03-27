package com.example.bgcpromogearreworked.api.officelocations.exceptions;

public class InventoryLevelNotFoundException extends RuntimeException {

    public InventoryLevelNotFoundException() {
        super("Inventory level not found.");
    }
}
