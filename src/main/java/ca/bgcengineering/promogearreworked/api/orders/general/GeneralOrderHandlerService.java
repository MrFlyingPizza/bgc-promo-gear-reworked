package ca.bgcengineering.promogearreworked.api.orders.general;

import ca.bgcengineering.promogearreworked.api.inventorylevels.globalinventorylevel.GlobalInventoryLevelService;
import ca.bgcengineering.promogearreworked.api.orders.aspects.orderquantityprocessing.ItemPartitions;
import ca.bgcengineering.promogearreworked.api.orders.aspects.orderquantityprocessing.OrderQuantityProcessor;
import ca.bgcengineering.promogearreworked.api.orders.general.dto.GeneralOrderCreate;
import ca.bgcengineering.promogearreworked.persistence.entities.GlobalInventoryLevel;
import ca.bgcengineering.promogearreworked.persistence.entities.Order;
import ca.bgcengineering.promogearreworked.api.orders.OrderService;
import ca.bgcengineering.promogearreworked.api.orders.general.dto.GeneralOrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Validated
@RequiredArgsConstructor
public class GeneralOrderHandlerService {

    private final OrderService service;
    private final GlobalInventoryLevelService inventoryService;
    private final GeneralOrderMapper mapper;
    private final OrderQuantityProcessor quantityProcessor;

    private void splitOrderItems(List<GeneralOrderCreate.NestedOrderItem> orderItems,
                                 List<GeneralOrderCreate.NestedOrderItem> readyPortion,
                                 List<GeneralOrderCreate.NestedOrderItem> waitingPortion) {
        for (GeneralOrderCreate.NestedOrderItem orderItem : orderItems) {
            GlobalInventoryLevel inventoryLevel = inventoryService.getGlobalInventoryLevel(orderItem.getVariantId());
            if (inventoryLevel.getApparentQuantity() >= orderItem.getQuantity()) {
                readyPortion.add(orderItem);
            } else if (inventoryLevel.getApparentQuantity() > 0) {
                int availableAmount = inventoryLevel.getApparentQuantity();
                readyPortion.add(orderItem.toBuilder().quantity(availableAmount).build());
                int waitingAmount = orderItem.getQuantity() - availableAmount;
                waitingPortion.add(orderItem.toBuilder().quantity(waitingAmount).build());
            } else {
                waitingPortion.add(orderItem);
            }
        }
    }

    private List<GeneralOrderCreate> splitOrder(GeneralOrderCreate orderCreate) {

        ItemPartitions<GeneralOrderCreate.NestedOrderItem> partition = quantityProcessor.partition(orderCreate.getItems(), GeneralOrderCreate.NestedOrderItem.class);

        List<GeneralOrderCreate.NestedOrderItem> submissionItems = partition.getAvailable();
        List<GeneralOrderCreate.NestedOrderItem> waitingItems = partition.getUnavailable();

        List<GeneralOrderCreate> orders = new ArrayList<>();
        if (!submissionItems.isEmpty()) {
            orders.add(orderCreate.toBuilder().items(submissionItems).status(Order.Status.SUBMITTED).build());
        }
        if (!waitingItems.isEmpty()) {
            orders.add(orderCreate.toBuilder().items(waitingItems).status(Order.Status.WAIT_LIST).build());
        }
        return orders;
    }

    @Transactional
    List<Order> handleOrderCreate(@Valid GeneralOrderCreate orderCreate) {
        return splitOrder(orderCreate).stream().map(order -> service.createOrder(order, mapper::fromCreate))
                .collect(Collectors.toList());
    }

    @Transactional
    List<Order> handleOrderBatchGet(Long userId) {
        return service.getOrders(userId);
    }

    @Transactional
    Order handleOrderGet(Long userId, Long orderId) {
        return service.getOrder(userId, orderId);
    }

}
