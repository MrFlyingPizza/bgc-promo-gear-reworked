package com.example.bgcpromogearreworked.api.orders.operations.inventory.modification;

import com.example.bgcpromogearreworked.persistence.entities.Order;
import org.springframework.stereotype.Component;

@Component
public class StatusUpdateInventoryModifierProvider {

    public StatusUpdateInventoryModifier get(Order.Status previous, Order.Status current) {
        return (inventoryLevel, quantity) -> {
            InventoryModificationWrapper modifications = InventoryModificationWrapper.of(inventoryLevel, quantity);
            if (previous != null) {
                InventoryModifiers.get(previous).negate(modifications);
            }
            if (current != null) {
                InventoryModifiers.get(current).apply(modifications);
            }
            return modifications.get();
        };
    }

}
