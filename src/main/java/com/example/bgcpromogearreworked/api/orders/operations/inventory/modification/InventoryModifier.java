package com.example.bgcpromogearreworked.api.orders.operations.inventory.modification;

interface InventoryModifier {

    /**
     * An inventory modifier that does nothing. Used for
     */
    InventoryModifier NO_MODIFICATION = new InventoryModifier() {
        @Override
        public void apply(InventoryModificationWrapper inventoryModificationWrapper) { }

        @Override
        public void negate(InventoryModificationWrapper inventoryModificationWrapper) { }
    };

    void apply(InventoryModificationWrapper inventoryModificationWrapper);
    void negate(InventoryModificationWrapper inventoryModificationWrapper);

}