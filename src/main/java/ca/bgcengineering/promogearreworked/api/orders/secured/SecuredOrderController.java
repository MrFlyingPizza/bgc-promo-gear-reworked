package ca.bgcengineering.promogearreworked.api.orders.secured;

import ca.bgcengineering.promogearreworked.api.orders.OrderService;
import ca.bgcengineering.promogearreworked.api.orders.exceptions.OrderNotFoundException;
import ca.bgcengineering.promogearreworked.api.orders.secured.dto.*;
import ca.bgcengineering.promogearreworked.persistence.entities.Order;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/secured/orders", produces = MediaType.APPLICATION_JSON_VALUE)
public class SecuredOrderController {

    private final SecuredOrderHandlerService handlerService;
    private final OrderService service;
    private final SecuredOrderMapper mapper;

    @PostMapping
    private SecuredOrderResponse createOrder(SecuredOrderCreate orderCreate) {
        return null;
    }

    @GetMapping
    private SecuredOrderBatchResponse getOrderBatch(@RequestParam(defaultValue = "false") Boolean paged,
                                               @QuerydslPredicate(root = Order.class) Predicate predicate,
                                               Pageable pageable) {
        if (paged) {
            pageable = Pageable.unpaged();
        }
        return mapper.toBatchResponse(handlerService.handleOrderBatchGet(predicate, pageable));
    }

    @GetMapping("/{orderId}")
    private SecuredOrderResponse getOrder(@PathVariable Long orderId) {
        if (!service.checkOrderExists(orderId)) {
            throw new OrderNotFoundException();
        }
        return mapper.toResponse(handlerService.handleOrderGet(orderId));
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
