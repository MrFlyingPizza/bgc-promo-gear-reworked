package com.example.bgcpromogearreworked.api.orders;

import com.example.bgcpromogearreworked.api.orders.operations.inventory.modification.StatusUpdateInventoryModifier;
import com.example.bgcpromogearreworked.api.orders.operations.inventory.modification.StatusUpdateInventoryModifierProvider;
import com.example.bgcpromogearreworked.persistence.entities.InventoryLevel;
import com.example.bgcpromogearreworked.persistence.entities.InventoryLevelId;
import com.example.bgcpromogearreworked.persistence.entities.Order;
import com.example.bgcpromogearreworked.persistence.entities.OrderItem;
import com.example.bgcpromogearreworked.persistence.repositories.InventoryLevelRepository;
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
