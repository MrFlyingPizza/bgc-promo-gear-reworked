package com.example.bgcpromogearreworked.api.orders.inventorymanagement;

import com.example.bgcpromogearreworked.persistence.entities.InventoryLevel;
import com.example.bgcpromogearreworked.persistence.entities.InventoryLevelId;
import com.example.bgcpromogearreworked.persistence.entities.Order;
import com.example.bgcpromogearreworked.persistence.entities.OrderItem;
import com.example.bgcpromogearreworked.persistence.repositories.InventoryLevelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderInventoryManagerService {

    private final InventoryLevelRepository inventoryRepo;
    private final InventoryModifierSupplier modifierSupplier;

    public void manage(Order order, Order.Status previousStatus) {

        final Long locationId = order.getLocation().getId();

        InventoryModifier modifier = modifierSupplier.get(order.getStatus(), previousStatus);
        // TODO: 2022-04-06 implement fully
        for (OrderItem orderItem : order.getOrderItems()) {
            InventoryLevelId id = new InventoryLevelId(locationId, orderItem.getVariantId());
            InventoryLevel inventoryLevel = inventoryRepo.findById(id).orElseThrow();
            modifier.apply(inventoryLevel, orderItem.getQuantity());
            inventoryRepo.save(inventoryLevel);
        }
    }

}
