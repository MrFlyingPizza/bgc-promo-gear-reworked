package com.example.bgcpromogearreworked.api.orders;

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
        void apply(InventoryLevel inventoryLevel, Integer quantity);
    }

    private void processInventoryQuantities(Order order) {
        Long locationId = order.getLocation().getId();
        QuantityUpdateCallback quantityUpdateCallback;
        switch (order.getStatus()) {
            case WAIT_LIST:
                quantityUpdateCallback = (inventoryLevel, quantity) -> inventoryLevel.setNeededQuantity(inventoryLevel.getNeededQuantity() + quantity);
                break;
            case SUBMITTED:
                quantityUpdateCallback = (inventoryLevel, quantity) -> inventoryLevel.setAvailableQuantity(inventoryLevel.getAvailableQuantity() - quantity);
                break;
            default:
                throw new RuntimeException(String.format("Unexpected status when processing inventory quantities: %s",
                        order.getStatus()));
        }

        for (OrderItem orderItem : order.getOrderItems()) {
            InventoryLevelId id = new InventoryLevelId(locationId, orderItem.getVariantId());
            InventoryLevel inventoryLevel = inventoryRepo.findById(id).orElseThrow();
            quantityUpdateCallback.apply(inventoryLevel, orderItem.getQuantity());
            inventoryRepo.save(inventoryLevel);
        }
    }

    @Transactional
    public <T> Order createOrder(T source, Function<T, Order> mapper) {
        assert source != null && mapper != null;
        Order order = mapper.apply(source);
        assert order.getId() == null;
        processInventoryQuantities(order);
        return orderRepo.saveAndFlush(order);
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
