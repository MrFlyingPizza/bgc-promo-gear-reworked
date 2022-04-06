package com.example.bgcpromogearreworked.api.orders;

import com.example.bgcpromogearreworked.api.orders.exceptions.OrderNotFoundException;
import com.example.bgcpromogearreworked.api.orders.inventorymanagement.OrderInventoryManagerService;
import com.example.bgcpromogearreworked.persistence.entities.Order;
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
    private final OrderInventoryManagerService inventoryManager;

    public boolean checkOrderExists(Long orderId) {
        return orderRepo.existsById(orderId);
    }

    public boolean checkOrderExistsOnUser(Long userId, Long orderId) {
        return orderRepo.existsBySubmitterIdAndId(userId, orderId);
    }

    @Transactional
    public <T> Order createOrder(T source, Function<T, Order> mapper) {
        assert source != null && mapper != null;
        Order order = mapper.apply(source);
        assert order.getId() == null;
        inventoryManager.manage(order, null);
        return orderRepo.saveAndFlush(order);
    }

    @Transactional
    public <T> Order updateOrder(Long orderId, T source, BiFunction<T, Order, Order> mapper) {
        assert orderId != null && source != null && mapper != null;
        Order order = orderRepo.findById(orderId).orElseThrow(OrderNotFoundException::new);
        final Order.Status previousStatus = order.getStatus();
        Order updatedOrder = mapper.apply(source, order);
        assert order.getId().equals(orderId);
        inventoryManager.manage(order, previousStatus);
        return orderRepo.saveAndFlush(updatedOrder);
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
