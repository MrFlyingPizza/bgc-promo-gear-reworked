package ca.bgcengineering.promogearreworked.api.orders.aspects.inventoryprocessing;

import ca.bgcengineering.promogearreworked.persistence.entities.Order;
import lombok.NonNull;
import org.springframework.stereotype.Component;

@Component
public class StatusUpdateInventoryModifierProvider {

    /**
     * Creates a {@link StatusUpdateInventoryModifier} that updates the inventory based on the change in order status.
     * @param current The current order status.
     * @param previous The previous order status
     * @return The inventory modifier.
     */
    public StatusUpdateInventoryModifier get(@NonNull final Order.Status current, @NonNull final Order.Status previous) {
        return (inventoryLevel, quantity) -> {
            InventoryModificationWrapper modifications = InventoryModificationWrapper.of(inventoryLevel, quantity);
            InventoryModifiers.get(previous).negate(modifications);
            InventoryModifiers.get(current).apply(modifications);
            return modifications.get();
        };
    }

    /**
     * Creates a {@link StatusUpdateInventoryModifier} that updates the inventory based on the status of a new order.
     * @param status The status of the new order.
     * @return The inventory modifier.
     */
    public StatusUpdateInventoryModifier get(@NonNull final Order.Status status) {
        return ((inventoryLevel, quantity) -> {
            InventoryModificationWrapper modifications = InventoryModificationWrapper.of(inventoryLevel, quantity);
            InventoryModifiers.get(status).apply(modifications);
            return modifications.get();
        });
    }

}
