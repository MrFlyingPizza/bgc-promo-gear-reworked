package com.example.bgcpromogearreworked.api.users.cartitem.general;

import com.example.bgcpromogearreworked.api.shared.utils.Utils;
import com.example.bgcpromogearreworked.api.users.cartitem.CartItemService;
import com.example.bgcpromogearreworked.api.users.cartitem.general.dto.*;
import com.example.bgcpromogearreworked.api.users.exceptions.UserAuthenticationClaimInvalidException;
import com.example.bgcpromogearreworked.api.users.exceptions.UserCartItemNotFoundException;
import com.example.bgcpromogearreworked.api.users.exceptions.UserNotAuthenticatedException;
import com.example.bgcpromogearreworked.api.users.exceptions.UserNotFoundException;
import com.example.bgcpromogearreworked.api.users.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "/api/users/cart_item", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class GeneralCartItemController {

    private final UserService userService;
    private final CartItemService service;
    private final GeneralCartItemHandlerService handlerService;
    private final GeneralCartItemMapper mapper;

    private static final String NO_OID = "No object ID claim found in the authenticated user.";

    private Long processAuthenticatedUser(OidcUser oidcUser) {
        if (oidcUser == null) {
            throw new UserNotAuthenticatedException();
        }
        UUID oid = Utils.oidFromOidcUser(oidcUser);
        if (oid == null) {
            throw new UserAuthenticationClaimInvalidException(NO_OID);
        }
        if (!userService.checkUserExists(oid)) {
            throw new UserNotFoundException();
        }
        return userService.getUser(oid).getId();
    }

    @PostMapping
    private GeneralCartItemResponse createCartItem(@RequestBody GeneralCartItemCreate cartItemCreate,
                                                   @AuthenticationPrincipal OidcUser oidcUser) {
        Long userId = processAuthenticatedUser(oidcUser);
        cartItemCreate.setUserId(userId);
        return mapper.toResponse(handlerService.handleCartItemCreate(cartItemCreate));
    }

    @GetMapping("/{variantId}")
    private GeneralCartItemResponse getCartItem(@PathVariable Long variantId,
                                                @AuthenticationPrincipal OidcUser oidcUser) {
        Long userId = processAuthenticatedUser(oidcUser);
        if (!service.checkCartItemExists(userId, variantId)) {
            throw new UserCartItemNotFoundException();
        }
        return mapper.toResponse(handlerService.handleCartItemGet(userId, variantId));
    }

    @GetMapping
    private GeneralCartItemBatchResponse getCartItemBatch(@AuthenticationPrincipal OidcUser oidcUser) {
        Long userId = processAuthenticatedUser(oidcUser);
        return mapper.toBatchResponse(handlerService.handleCartItemBatchGet(userId));
    }

    @PutMapping("/{variantId}")
    private GeneralCartItemResponse updateCartItem(@PathVariable Long variantId,
                                                 @RequestBody GeneralCartItemUpdate cartItemUpdate,
                                                 @AuthenticationPrincipal OidcUser oidcUser) {
        Long userId = processAuthenticatedUser(oidcUser);
        if (!service.checkCartItemExists(userId, variantId)) {
            throw new UserCartItemNotFoundException();
        }
        cartItemUpdate.setUserId(userId);
        cartItemUpdate.setVariantId(variantId);
        return mapper.toResponse(handlerService.handleCartItemUpdate(cartItemUpdate));
    }

    @DeleteMapping("/{variantId}")
    private void deleteCartItem(@PathVariable Long variantId, @AuthenticationPrincipal OidcUser oidcUser) {
        Long userId = processAuthenticatedUser(oidcUser);
        if (!service.checkCartItemExists(userId, variantId)) {
            throw new UserCartItemNotFoundException();
        }
        handlerService.handleCartItemDelete(userId, variantId);
    }

    @DeleteMapping
    private void deleteCartItemBatch(@AuthenticationPrincipal OidcUser oidcUser) {
        Long userId = processAuthenticatedUser(oidcUser);
        handlerService.handleCartItemBatchDelete(userId);
    }

}
