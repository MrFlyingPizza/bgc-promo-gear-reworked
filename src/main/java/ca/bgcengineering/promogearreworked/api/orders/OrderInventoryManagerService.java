package ca.bgcengineering.promogearreworked.api.orders;

import ca.bgcengineering.promogearreworked.api.orders.aspects.inventoryprocessing.StatusUpdateInventoryModifier;
import ca.bgcengineering.promogearreworked.api.orders.aspects.inventoryprocessing.StatusUpdateInventoryModifierProvider;
import ca.bgcengineering.promogearreworked.persistence.entities.Order;
import ca.bgcengineering.promogearreworked.persistence.entities.OrderItem;
import ca.bgcengineering.promogearreworked.persistence.entities.InventoryLevel;
import ca.bgcengineering.promogearreworked.persistence.entities.InventoryLevelId;
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
    public void manage(@NonNull Order currentOrder, Order previousOrder) {

        final Long locationId = currentOrder.getLocation().getId();
        final Order.Status currentStatus = currentOrder.getStatus();
        final Order.Status previousStatus = previousOrder == null ? null : previousOrder.getStatus();
        final Set<OrderItem> orderItems = currentOrder.getItems();

        StatusUpdateInventoryModifier modifier = modifierProvider.get(currentStatus, previousStatus);

        for (OrderItem orderItem : orderItems) {
            InventoryLevel inventoryLevel = getInventoryLevel(locationId, orderItem.getVariantId());
            inventoryLevel = modifier.apply(inventoryLevel, orderItem.getQuantity());
            inventoryRepo.save(inventoryLevel);
        }

    }

}
