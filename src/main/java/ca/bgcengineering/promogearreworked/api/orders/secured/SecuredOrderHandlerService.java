package ca.bgcengineering.promogearreworked.api.orders.secured;

import ca.bgcengineering.promogearreworked.api.orders.secured.dto.SecuredOrderMapper;
import ca.bgcengineering.promogearreworked.api.orders.secured.dto.SecuredOrderPartialUpdate;
import ca.bgcengineering.promogearreworked.api.orders.secured.dto.SecuredOrderUpdate;
import ca.bgcengineering.promogearreworked.persistence.entities.Order;
import ca.bgcengineering.promogearreworked.api.orders.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Service
@RequiredArgsConstructor
@Validated
public class SecuredOrderHandlerService {

    private final OrderService service;
    private final SecuredOrderMapper mapper;

    Order handleOrderPartialUpdate(@Valid SecuredOrderPartialUpdate orderPartialUpdate) {
        return service.updateOrder(orderPartialUpdate.getId(), orderPartialUpdate, mapper::fromPartialUpdate);
    }

    Order handleOrderUpdate(@Valid SecuredOrderUpdate orderUpdate) {
        // TODO: 2022-06-12 implement
        return null;
    }

}
