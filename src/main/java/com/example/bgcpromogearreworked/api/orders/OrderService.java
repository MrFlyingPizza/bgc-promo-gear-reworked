package com.example.bgcpromogearreworked.api.orders;

import com.example.bgcpromogearreworked.persistence.entities.Order;
import com.example.bgcpromogearreworked.persistence.repositories.CartItemRepository;
import com.example.bgcpromogearreworked.persistence.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepo;
    private final CartItemRepository cartItemRepo;

    @Transactional
    public <T> Order createOrder(T source, Function<T, Order> mapper) {
        assert source != null && mapper != null;
        Order order = mapper.apply(source);
        assert order.getId() == null;
        return orderRepo.saveAndFlush(order);
    }
// TODO: 2022-03-27 finish implement
}
