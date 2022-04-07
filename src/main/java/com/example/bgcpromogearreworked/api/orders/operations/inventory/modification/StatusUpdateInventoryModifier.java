package com.example.bgcpromogearreworked.api.orders.operations.inventory.modification;

import com.example.bgcpromogearreworked.persistence.entities.InventoryLevel;

public interface StatusUpdateInventoryModifier {
    InventoryLevel apply(InventoryLevel inventoryLevel, int quantity);
}
