package ca.bgcengineering.promogearreworked.api.orders.aspects.inventoryprocessing;

interface InventoryModifier {

    /**
     * An inventory modifier that does nothing.
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