package ca.bgcengineering.promogearreworked.api.orders.general;

import ca.bgcengineering.promogearreworked.api.orders.exceptions.NoCartItemException;
import ca.bgcengineering.promogearreworked.api.orders.exceptions.OrderNotFoundException;
import ca.bgcengineering.promogearreworked.api.orders.general.dto.GeneralOrderBatchResponse;
import ca.bgcengineering.promogearreworked.api.orders.general.dto.GeneralOrderCreate;
import ca.bgcengineering.promogearreworked.api.shared.authentication.SessionUserDetailsHelperService;
import ca.bgcengineering.promogearreworked.api.orders.OrderService;
import ca.bgcengineering.promogearreworked.api.orders.general.dto.GeneralOrderMapper;
import ca.bgcengineering.promogearreworked.api.orders.general.dto.GeneralOrderResponse;
import ca.bgcengineering.promogearreworked.api.users.cartitem.CartItemService;
import ca.bgcengineering.promogearreworked.persistence.entities.CartItem;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/orders", produces = MediaType.APPLICATION_JSON_VALUE)
public class GeneralOrderController {

    private final SessionUserDetailsHelperService userDetailsHelperService;
    private final CartItemService cartItemService;
    private final OrderService service;
    private final GeneralOrderHandlerService handlerService;
    private final GeneralOrderMapper mapper;

    @PostMapping
    private GeneralOrderBatchResponse createOrder(@RequestBody GeneralOrderCreate orderCreate,
                                                  @AuthenticationPrincipal OidcUser oidcUser) {
        Long userId = userDetailsHelperService.processAuthenticatedUser(oidcUser);
        List<CartItem> cartItems = cartItemService.getCartItems(userId);
        if (cartItems.isEmpty()) {
            throw new NoCartItemException();
        }
        orderCreate.setRecipientId(userId);
        orderCreate.setSubmitterId(userId);
        orderCreate.setItems(mapper.cartItemsToOrderItems(cartItems));
        return mapper.toBatchResponse(handlerService.handleOrderCreate(orderCreate));
    }

    @GetMapping
    private GeneralOrderBatchResponse getOrderBatch(@AuthenticationPrincipal OidcUser oidcUser) {
        Long userId = userDetailsHelperService.processAuthenticatedUser(oidcUser);
        return mapper.toBatchResponse(handlerService.handleOrderBatchGet(userId));
    }

    @GetMapping("/{orderId}")
    private GeneralOrderResponse getOrder(@PathVariable Long orderId, @AuthenticationPrincipal OidcUser oidcUser) {
        Long userId = userDetailsHelperService.processAuthenticatedUser(oidcUser);
        if (!service.checkOrderExistsOnUser(userId, orderId)) {
            throw new OrderNotFoundException();
        }
        return mapper.toResponse(handlerService.handleOrderGet(userId, orderId));
    }

}
