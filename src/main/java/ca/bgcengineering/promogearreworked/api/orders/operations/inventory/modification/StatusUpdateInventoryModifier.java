package ca.bgcengineering.promogearreworked.api.orders.operations.inventory.modification;

import ca.bgcengineering.promogearreworked.persistence.entities.InventoryLevel;

public interface StatusUpdateInventoryModifier {
    InventoryLevel apply(InventoryLevel inventoryLevel, int quantity);
}
