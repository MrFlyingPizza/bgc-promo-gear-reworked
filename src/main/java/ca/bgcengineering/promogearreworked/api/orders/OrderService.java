package ca.bgcengineering.promogearreworked.api.orders;

import ca.bgcengineering.promogearreworked.api.orders.exceptions.OrderNotFoundException;
import ca.bgcengineering.promogearreworked.persistence.entities.Order;
import ca.bgcengineering.promogearreworked.persistence.entities.OrderItem;
import ca.bgcengineering.promogearreworked.persistence.repositories.OrderExtraInfoRepository;
import ca.bgcengineering.promogearreworked.persistence.repositories.OrderItemRepository;
import ca.bgcengineering.promogearreworked.persistence.repositories.OrderRepository;
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
    private final OrderExtraInfoRepository extraInfoRepo;
    private final OrderItemRepository orderItemRepo;
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
        order = orderRepo.save(order);
        final Long orderId = order.getId();
        if (order.getExtraInfo() != null) {
            order.getExtraInfo().setOrderId(orderId);
            extraInfoRepo.save(order.getExtraInfo());
        }
        for (OrderItem item : order.getItems()) {
            item.setOrderId(orderId);
            orderItemRepo.save(item);
        }
        inventoryManager.manage(order, null);
        return order;
    }

    @Transactional
    public <T> Order updateOrder(Long orderId, T source, BiFunction<T, Order, Order> mapper) {
        assert orderId != null && source != null && mapper != null;
        Order order = orderRepo.findById(orderId).orElseThrow(OrderNotFoundException::new);
        Order previousOrder = order.toBuilder().build();
        Order updatedOrder = mapper.apply(source, order);
        assert order.getId().equals(orderId);
        inventoryManager.manage(order, previousOrder);
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
