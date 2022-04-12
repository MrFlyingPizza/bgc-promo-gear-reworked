package ca.bgcengineering.promogearreworked.api.inventorylevels.exceptions;

public class GlobalInventoryLevelNotFound extends RuntimeException {

    public GlobalInventoryLevelNotFound() {
        super("Global inventory level not found.");
    }
}
