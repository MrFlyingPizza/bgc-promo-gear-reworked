package ca.bgcengineering.promogearreworked.api.orders.secured;

import ca.bgcengineering.promogearreworked.persistence.entities.Order;
import ca.bgcengineering.promogearreworked.api.orders.OrderService;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@RequiredArgsConstructor
@Validated
public class SecuredOrderHandlerService {

    private final OrderService service;

    Page<Order> handleOrderBatchGet(Predicate predicate, Pageable pageable) {
        return service.getOrders(predicate, pageable);
    }

    Order handleOrderGet(Long orderId) {
        return service.getOrder(orderId);
    }

}
