package ca.bgcengineering.promogearreworked.api.inventorylevels.exceptions;

public class InventoryLevelNotFoundException extends RuntimeException {

    public InventoryLevelNotFoundException() {
        super("Inventory level not found.");
    }
}
