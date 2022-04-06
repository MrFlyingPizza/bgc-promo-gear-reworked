package com.example.bgcpromogearreworked.api.orders.inventorymanagement;

import com.example.bgcpromogearreworked.persistence.entities.Order;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
class InventoryModifierSupplier {

    // TODO: 2022-04-06 implement supplier functions
    private static final Map<StatusDeltaKey, InventoryModifier> callbacks = Map.ofEntries(
            Map.entry(new StatusDeltaKey(Order.Status.WAIT_LIST, null), (inventoryLevel, change) -> {
        inventoryLevel.setNeededQuantity(inventoryLevel.getNeededQuantity() + change);
        return inventoryLevel;
    }), Map.entry(new StatusDeltaKey(Order.Status.SUBMITTED, Order.Status.WAIT_LIST), (inventoryLevel, change) -> {
        return inventoryLevel;
    }), Map.entry(new StatusDeltaKey(Order.Status.PROCESSING, Order.Status.SUBMITTED), (inventoryLevel, change) -> {
        return inventoryLevel;
    }), Map.entry(new StatusDeltaKey(Order.Status.COMPLETED, Order.Status.PROCESSING), (inventoryLevel, change) -> {
        return inventoryLevel;
    }), Map.entry(new StatusDeltaKey(Order.Status.CANCELLED, Order.Status.WAIT_LIST), (inventoryLevel, change) -> {
        return inventoryLevel;
    }), Map.entry(new StatusDeltaKey(Order.Status.CANCELLED, Order.Status.SUBMITTED), (inventoryLevel, change) -> {
        return inventoryLevel;
    }), Map.entry(new StatusDeltaKey(Order.Status.CANCELLED, Order.Status.PROCESSING), (inventoryLevel, change) -> {
        return inventoryLevel;
    }), Map.entry(new StatusDeltaKey(Order.Status.COMPLETED, null), (inventoryLevel, change) -> {
        return inventoryLevel;
    }), Map.entry(new StatusDeltaKey(Order.Status.SUBMITTED, null), (inventoryLevel, change) -> {
        return inventoryLevel;
    }), Map.entry(new StatusDeltaKey(Order.Status.PROCESSING, null), (inventoryLevel, change) -> {
        return inventoryLevel;
    }), Map.entry(new StatusDeltaKey(Order.Status.WAIT_LIST, Order.Status.WAIT_LIST), (inventoryLevel, change) -> {
        return inventoryLevel;
    }), Map.entry(new StatusDeltaKey(Order.Status.PROCESSING, Order.Status.PROCESSING), (inventoryLevel, change) -> {
        return inventoryLevel;
    }), Map.entry(new StatusDeltaKey(Order.Status.SUBMITTED, Order.Status.SUBMITTED), (inventoryLevel, change) -> {
        return inventoryLevel;
    }));

    InventoryModifier get(Order.Status previousStatus, Order.Status currentStatus) {
        return callbacks.get(new StatusDeltaKey(previousStatus, currentStatus));
    }

}
