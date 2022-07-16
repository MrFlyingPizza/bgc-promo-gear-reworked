package ca.bgcengineering.promogearreworked.api.orders.aspects.inventoryprocessing;

import ca.bgcengineering.promogearreworked.persistence.entities.Order;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

enum InventoryModifiers {

    WAIT_LIST(Order.Status.WAIT_LIST, new InventoryModifier() {
        @Override
        public void apply(InventoryModificationWrapper changes) {
            changes.addToNeeded();
        }

        @Override
        public void negate(InventoryModificationWrapper changes) {
            changes.removeFromNeeded();
        }
    }),
    SUBMITTED(Order.Status.SUBMITTED, new InventoryModifier() {
        @Override
        public void apply(InventoryModificationWrapper changes) {
            changes.addToReserved().removeFromAvailable();
        }

        @Override
        public void negate(InventoryModificationWrapper changes) {
            changes.removeFromReserved().addToAvailable();
        }
    }),
    PROCESSING(Order.Status.PROCESSING, new InventoryModifier() {
        @Override
        public void apply(InventoryModificationWrapper changes) {
            changes.addToReserved().removeFromAvailable();
        }

        @Override
        public void negate(InventoryModificationWrapper changes) {
            changes.removeFromReserved().addToAvailable();
        }
    }),
    COMPLETED(Order.Status.COMPLETED, new InventoryModifier() {
        @Override
        public void apply(InventoryModificationWrapper changes) {
            changes.removeFromAvailable();
        }

        @Override
        public void negate(InventoryModificationWrapper changes) {
            changes.addToAvailable();
        }
    }),
    CANCELLED(Order.Status.CANCELLED, InventoryModifier.NO_MODIFICATION);

    private static final Map<Order.Status, InventoryModifier> modifiers = Stream.of(values())
            .collect(Collectors.toUnmodifiableMap(modifiers -> modifiers.status , modifiers -> modifiers.modifier));

    private final Order.Status status;
    private final InventoryModifier modifier;

    static InventoryModifier get(Order.Status status) {
        return modifiers.get(status);
    }

    InventoryModifiers(Order.Status status, InventoryModifier modifier) {
        this.status = status;
        this.modifier = modifier;
    }
}
