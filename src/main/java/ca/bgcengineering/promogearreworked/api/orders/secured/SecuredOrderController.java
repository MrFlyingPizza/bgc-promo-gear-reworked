package ca.bgcengineering.promogearreworked.api.orders.secured;

import ca.bgcengineering.promogearreworked.api.orders.OrderService;
import ca.bgcengineering.promogearreworked.api.orders.exceptions.OrderNotFoundException;
import ca.bgcengineering.promogearreworked.api.orders.secured.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/secured/orders", produces = MediaType.APPLICATION_JSON_VALUE)
public class SecuredOrderController {

    private final SecuredOrderHandlerService handlerService;
    private final SecuredOrderMapper mapper;
    private final OrderService service;

    @PostMapping
    private SecuredOrderResponse createOrder(SecuredOrderCreate orderCreate) {
        return null;
    }

    @PutMapping("/{orderId}")
    private SecuredOrderResponse updateOrder(@PathVariable Long orderId, SecuredOrderUpdate orderUpdate) {
        if (!service.checkOrderExists(orderId)) {
            throw new OrderNotFoundException();
        }
        orderUpdate.setId(orderId);
        return mapper.toResponse(handlerService.handleOrderUpdate(orderUpdate));
    }

    @PatchMapping("/{orderId}")
    private SecuredOrderResponse updateOrderPartial(@PathVariable Long orderId, SecuredOrderPartialUpdate orderPartialUpdate) {
        if (!service.checkOrderExists(orderId)) {
            throw new OrderNotFoundException();
        }
        orderPartialUpdate.setId(orderId);
        return mapper.toResponse(handlerService.handleOrderPartialUpdate(orderPartialUpdate));
    }

}
