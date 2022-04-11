package ca.bgcengineering.promogearreworked.api.orders.operations.inventory.modification;

import ca.bgcengineering.promogearreworked.persistence.entities.Order;
import org.springframework.stereotype.Component;

@Component
public class StatusUpdateInventoryModifierProvider {

    public StatusUpdateInventoryModifier get(Order.Status current, Order.Status previous) {
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
