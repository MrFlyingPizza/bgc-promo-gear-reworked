package ca.bgcengineering.promogearreworked.api.orders.aspects.orderpartitioning;

import ca.bgcengineering.promogearreworked.api.inventorylevels.globalinventorylevel.GlobalInventoryLevelService;
import ca.bgcengineering.promogearreworked.persistence.entities.GlobalInventoryLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderQuantityProcessor {

    private final GlobalInventoryLevelService inventoryService;

    private <T> ProcessableItemPartitions<T> partitionItemsByAvailability(List<ProcessableItem<T>> items) {
        ProcessableItemPartitions<T> partitions = new ProcessableItemPartitions<>();
        for (ProcessableItem<T> item : items) {
            GlobalInventoryLevel inventoryLevel = inventoryService.getGlobalInventoryLevel(item.getVariantId());
            if (inventoryLevel.getApparentQuantity() >= item.getQuantity()) {
                partitions.available.add(item);
            } else if (inventoryLevel.getApparentQuantity() > 0) {
                int availableAmount = inventoryLevel.getApparentQuantity();
                partitions.available.add(item.partition(availableAmount));
                int waitingAmount = item.getQuantity() - availableAmount;
                partitions.unavailable.add(item.partition(waitingAmount));
            } else {
                partitions.unavailable.add(item);
            }
        }
        return partitions;
    }

    public <T> ItemPartitions<T> partition(List<T> items, Class<T> itemClass) {
        return partitionItemsByAvailability(ProcessableItemBuilder.get(itemClass).construct(items)).toItemPartitions();
    }

}
