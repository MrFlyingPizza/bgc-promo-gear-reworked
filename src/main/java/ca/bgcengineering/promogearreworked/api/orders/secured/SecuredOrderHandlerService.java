package ca.bgcengineering.promogearreworked.api.orders.secured;

import ca.bgcengineering.promogearreworked.api.orders.OrderService;
import ca.bgcengineering.promogearreworked.api.orders.secured.dto.SecuredOrderMapper;
import ca.bgcengineering.promogearreworked.api.orders.secured.dto.SecuredOrderPartialUpdate;
import ca.bgcengineering.promogearreworked.api.orders.secured.dto.SecuredOrderUpdate;
import ca.bgcengineering.promogearreworked.persistence.entities.Order;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Service
@RequiredArgsConstructor
@Validated
public class SecuredOrderHandlerService {

    private final OrderService service;
    private final SecuredOrderMapper mapper;

    Page<Order> handleOrderBatchGet(Predicate predicate, Pageable pageable) {
        return service.getOrders(predicate, pageable);
    }

    Order handleOrderGet(Long orderId) {
        return service.getOrder(orderId);
    }

    Order handleOrderPartialUpdate(@Valid SecuredOrderPartialUpdate orderPartialUpdate) {
        return service.updateOrder(orderPartialUpdate.getId(), orderPartialUpdate, mapper::fromPartialUpdate);
    }

    Order handleOrderUpdate(@Valid SecuredOrderUpdate orderUpdate) {
        return service.updateOrder(orderUpdate.getId(), orderUpdate, mapper::fromUpdate);
    }

}
