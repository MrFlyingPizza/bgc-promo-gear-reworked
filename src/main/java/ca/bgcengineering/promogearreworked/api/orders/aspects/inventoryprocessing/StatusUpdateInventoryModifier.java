package ca.bgcengineering.promogearreworked.api.orders.aspects.inventoryprocessing;

import ca.bgcengineering.promogearreworked.persistence.entities.InventoryLevel;

public interface StatusUpdateInventoryModifier {
    InventoryLevel apply(InventoryLevel inventoryLevel, int quantity);
}
