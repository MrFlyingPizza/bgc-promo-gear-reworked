package com.example.bgcpromogearreworked.api.orders;

import com.example.bgcpromogearreworked.api.orders.exceptions.OrderNotFoundException;
import com.example.bgcpromogearreworked.persistence.entities.InventoryLevel;
import com.example.bgcpromogearreworked.persistence.entities.InventoryLevelId;
import com.example.bgcpromogearreworked.persistence.entities.Order;
import com.example.bgcpromogearreworked.persistence.entities.OrderItem;
import com.example.bgcpromogearreworked.persistence.repositories.InventoryLevelRepository;
import com.example.bgcpromogearreworked.persistence.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepo;
    private final InventoryLevelRepository inventoryRepo;

    public boolean checkOrderExists(Long orderId) {
        return orderRepo.existsById(orderId);
    }

    public boolean checkOrderExistsOnUser(Long userId, Long orderId) {
        return orderRepo.existsBySubmitterIdAndId(userId, orderId);
    }

    private interface QuantityUpdateCallback {
        void apply(InventoryLevel inventoryLevel, Order.Status previousStatus, Integer quantity);
    }

    private void processInventoryQuantities(Order order, Order.Status previousStatus) {
        Long locationId = order.getLocation().getId();
        QuantityUpdateCallback quantityUpdateCallback = null;
        switch (order.getStatus()) {
            case WAIT_LIST:
                if (previousStatus == null) {
                    quantityUpdateCallback = (inventoryLevel, previous, quantity) ->
                            inventoryLevel.setNeededQuantity(inventoryLevel.getNeededQuantity() + quantity);
                }
                break;
            case SUBMITTED:
                quantityUpdateCallback = (inventoryLevel, previous, quantity) -> {
                    if (previous == Order.Status.WAIT_LIST) {
                        inventoryLevel.setNeededQuantity(inventoryLevel.getNeededQuantity() - quantity);
                    }
                    inventoryLevel.setAvailableQuantity(inventoryLevel.getAvailableQuantity() - quantity);
                    inventoryLevel.setReservedQuantity(inventoryLevel.getReservedQuantity() + quantity);
                };
                break;
            case PROCESSING:
                break;
            case COMPLETED:
                if (previousStatus == Order.Status.PROCESSING || previousStatus == Order.Status.SUBMITTED) {
                    quantityUpdateCallback = (inventoryLevel, previous, quantity) ->
                            inventoryLevel.setReservedQuantity(inventoryLevel.getReservedQuantity() - quantity);
                } else {
                    quantityUpdateCallback = (inventoryLevel, previousStatus1, quantity) ->
                            inventoryLevel.setAvailableQuantity(inventoryLevel.getAvailableQuantity() - quantity);
                }
                break;
            case CANCELLED:
                if (previousStatus == Order.Status.WAIT_LIST) {

                } else if (previousStatus == Order.Status.SUBMITTED) {

                } else if (previousStatus == Order.Status.PROCESSING) {

                }
                break;
            default:
                throw new RuntimeException(String.format("Unexpected status when processing inventory quantities: %s",
                        order.getStatus()));
        }

        if (quantityUpdateCallback == null) {
            return;
        }

        for (OrderItem orderItem : order.getOrderItems()) {
            InventoryLevelId id = new InventoryLevelId(locationId, orderItem.getVariantId());
            InventoryLevel inventoryLevel = inventoryRepo.findById(id).orElseThrow();
            quantityUpdateCallback.apply(inventoryLevel, previousStatus, orderItem.getQuantity());
            inventoryRepo.save(inventoryLevel);
        }
    }

    @Transactional
    public <T> Order createOrder(T source, Function<T, Order> mapper) {
        assert source != null && mapper != null;
        Order order = mapper.apply(source);
        assert order.getId() == null;
        processInventoryQuantities(order, null);
        return orderRepo.saveAndFlush(order);
    }

    @Transactional
    public <T> Order updateOrder(Long orderId, T source, BiFunction<T, Order, Order> mapper) {
        assert orderId != null && source != null && mapper != null;
        Order order = orderRepo.findById(orderId).orElseThrow(OrderNotFoundException::new);
        return null;
    }

    @Transactional
    public List<Order> getOrders(Long userId) {
        assert userId != null;
        return orderRepo.findAllBySubmitterId(userId);
    }

    @Transactional
    public Order getOrder(Long userId, Long orderId) {
        assert userId != null && orderId != null;
        return orderRepo.findBySubmitterIdAndId(userId, orderId);
    }

}
