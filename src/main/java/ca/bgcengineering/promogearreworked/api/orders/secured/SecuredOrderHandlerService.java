package ca.bgcengineering.promogearreworked.api.orders.secured;

import ca.bgcengineering.promogearreworked.persistence.entities.Order;
import ca.bgcengineering.promogearreworked.api.orders.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@RequiredArgsConstructor
@Validated
public class SecuredOrderHandlerService {

    private final OrderService service;

    Order handleOrderUpdate() {
        return null;
    }

}
