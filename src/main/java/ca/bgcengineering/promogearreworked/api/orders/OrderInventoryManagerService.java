package ca.bgcengineering.promogearreworked.api.orders;

import ca.bgcengineering.promogearreworked.api.orders.aspects.inventoryprocessing.StatusUpdateInventoryModifier;
import ca.bgcengineering.promogearreworked.api.orders.aspects.inventoryprocessing.StatusUpdateInventoryModifierProvider;
import ca.bgcengineering.promogearreworked.persistence.entities.InventoryLevel;
import ca.bgcengineering.promogearreworked.persistence.entities.InventoryLevelId;
import ca.bgcengineering.promogearreworked.persistence.entities.Order;
import ca.bgcengineering.promogearreworked.persistence.entities.OrderItem;
import ca.bgcengineering.promogearreworked.persistence.repositories.InventoryLevelRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class OrderInventoryManagerService {

    private final InventoryLevelRepository inventoryRepo;
    private final StatusUpdateInventoryModifierProvider modifierProvider;

    private InventoryLevel getInventoryLevel(Long locationId, Long variantId) {
        return inventoryRepo.findById(new InventoryLevelId(locationId, variantId)).orElseThrow();
    }

    @Transactional
    protected void process(Set<OrderItem> orderItems, Long locationId, StatusUpdateInventoryModifier modifier) {

        for (OrderItem orderItem : orderItems) {
            InventoryLevel inventoryLevel = getInventoryLevel(locationId, orderItem.getVariantId());
            inventoryLevel = modifier.apply(inventoryLevel, orderItem.getQuantity());
            inventoryRepo.save(inventoryLevel);
        }

    }

    public void manage(@NonNull Order currentOrder, @NonNull Order previousOrder) {

        final Long locationId = currentOrder.getLocation().getId();
        final Order.Status currentStatus = currentOrder.getStatus();
        final Order.Status previousStatus = previousOrder.getStatus();
        final Set<OrderItem> orderItems = currentOrder.getItems();
        StatusUpdateInventoryModifier modifier = modifierProvider.get(currentStatus, previousStatus);
        process(orderItems, locationId, modifier);
    }

    public void manage(@NonNull Order currentOrder) {

        final Long locationId = currentOrder.getLocation().getId();
        final Order.Status status = currentOrder.getStatus();
        final Set<OrderItem> orderItems = currentOrder.getItems();
        StatusUpdateInventoryModifier modifier = modifierProvider.get(status);
        process(orderItems, locationId, modifier);
    }

}
