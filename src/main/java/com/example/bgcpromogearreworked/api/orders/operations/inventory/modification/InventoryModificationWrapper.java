package com.example.bgcpromogearreworked.api.orders.operations.inventory.modification;

import com.example.bgcpromogearreworked.persistence.entities.InventoryLevel;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
class InventoryModificationWrapper {

    private InventoryLevel inventoryLevel;
    private int change;

    static InventoryModificationWrapper of(InventoryLevel inventoryLevel, int change) {
        return new InventoryModificationWrapper(inventoryLevel, change);
    }

    InventoryModificationWrapper addToAvailable() {
        inventoryLevel.setAvailableQuantity(inventoryLevel.getAvailableQuantity() + change);
        return this;
    }

    InventoryModificationWrapper removeFromAvailable() {
        inventoryLevel.setAvailableQuantity(inventoryLevel.getAvailableQuantity() - change);
        return this;
    }

    InventoryModificationWrapper addToReserved() {
        inventoryLevel.setReservedQuantity(inventoryLevel.getReservedQuantity() + change);
        return this;
    }

    InventoryModificationWrapper removeFromReserved() {
        inventoryLevel.setReservedQuantity(inventoryLevel.getReservedQuantity() - change);
        return this;
    }

    InventoryModificationWrapper addToNeeded() {
        inventoryLevel.setNeededQuantity(inventoryLevel.getNeededQuantity() + change);
        return this;
    }

    InventoryModificationWrapper removeFromNeeded() {
        inventoryLevel.setNeededQuantity(inventoryLevel.getNeededQuantity() - change);
        return this;
    }

    InventoryLevel get() {
        return inventoryLevel;
    }

}
