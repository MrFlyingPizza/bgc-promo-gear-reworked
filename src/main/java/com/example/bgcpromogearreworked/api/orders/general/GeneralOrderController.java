package com.example.bgcpromogearreworked.api.orders.general;

import com.example.bgcpromogearreworked.api.orders.OrderService;
import com.example.bgcpromogearreworked.api.orders.general.dto.GeneralOrderCreate;
import com.example.bgcpromogearreworked.api.orders.general.dto.GeneralOrderMapper;
import com.example.bgcpromogearreworked.api.orders.general.dto.GeneralOrderResponse;
import com.example.bgcpromogearreworked.api.shared.authentication.SessionUserDetailsHelperService;
import com.example.bgcpromogearreworked.api.users.cartitem.CartItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders/")
public class GeneralOrderController {

    private final SessionUserDetailsHelperService userDetailsHelperService;
    private final CartItemService cartItemService;
    private final OrderService service;
    private final GeneralOrderHandlerService handlerService;
    private final GeneralOrderMapper mapper;

    // TODO: 2022-03-27 finish implement
    @PostMapping
    private GeneralOrderResponse createOrder(@RequestBody GeneralOrderCreate orderCreate,
                                             @AuthenticationPrincipal OidcUser oidcUser) {
        Long userId = userDetailsHelperService.processAuthenticatedUser(oidcUser);
        orderCreate.setRecipientId(userId);
        orderCreate.setSubmitterId(userId);
        orderCreate.setItems(mapper.cartItemsToOrderItems(cartItemService.getCartItems(userId)));
        return null;
    }

}
