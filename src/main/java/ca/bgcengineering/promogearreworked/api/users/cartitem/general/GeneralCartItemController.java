package ca.bgcengineering.promogearreworked.api.users.cartitem.general;

import ca.bgcengineering.promogearreworked.api.shared.authentication.SessionUserDetailsHelperService;
import ca.bgcengineering.promogearreworked.api.users.cartitem.CartItemService;
import ca.bgcengineering.promogearreworked.api.users.cartitem.general.dto.*;
import ca.bgcengineering.promogearreworked.api.users.exceptions.UserCartItemNotFoundException;
import ca.bgcengineering.promogearreworked.api.users.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/users/me/cart_items", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class GeneralCartItemController {

    private final SessionUserDetailsHelperService userDetailsHelperService;
    private final UserService userService;
    private final CartItemService service;
    private final GeneralCartItemHandlerService handlerService;
    private final GeneralCartItemMapper mapper;

    @PostMapping
    private GeneralCartItemResponse createCartItem(@RequestBody GeneralCartItemCreate cartItemCreate,
                                                   @AuthenticationPrincipal OidcUser oidcUser) {
        Long userId = userDetailsHelperService.processAuthenticatedUser(oidcUser);
        cartItemCreate.setUserId(userId);
        return mapper.toResponse(handlerService.handleCartItemCreate(cartItemCreate));
    }

    @GetMapping("/{variantId}")
    private GeneralCartItemResponse getCartItem(@PathVariable Long variantId,
                                                @AuthenticationPrincipal OidcUser oidcUser) {
        Long userId = userDetailsHelperService.processAuthenticatedUser(oidcUser);
        if (!service.checkCartItemExists(userId, variantId)) {
            throw new UserCartItemNotFoundException();
        }
        return mapper.toResponse(handlerService.handleCartItemGet(userId, variantId));
    }

    @GetMapping
    private GeneralCartItemBatchResponse getCartItemBatch(@AuthenticationPrincipal OidcUser oidcUser) {
        Long userId = userDetailsHelperService.processAuthenticatedUser(oidcUser);
        return mapper.toBatchResponse(handlerService.handleCartItemBatchGet(userId));
    }

    @PutMapping("/{variantId}")
    private GeneralCartItemResponse updateCartItem(@PathVariable Long variantId,
                                                 @RequestBody GeneralCartItemUpdate cartItemUpdate,
                                                 @AuthenticationPrincipal OidcUser oidcUser) {
        Long userId = userDetailsHelperService.processAuthenticatedUser(oidcUser);
        if (!service.checkCartItemExists(userId, variantId)) {
            throw new UserCartItemNotFoundException();
        }
        cartItemUpdate.setUserId(userId);
        cartItemUpdate.setVariantId(variantId);
        return mapper.toResponse(handlerService.handleCartItemUpdate(cartItemUpdate));
    }

    @DeleteMapping("/{variantId}")
    private void deleteCartItem(@PathVariable Long variantId, @AuthenticationPrincipal OidcUser oidcUser) {
        Long userId = userDetailsHelperService.processAuthenticatedUser(oidcUser);
        if (!service.checkCartItemExists(userId, variantId)) {
            throw new UserCartItemNotFoundException();
        }
        handlerService.handleCartItemDelete(userId, variantId);
    }

    @DeleteMapping
    private void deleteCartItemBatch(@AuthenticationPrincipal OidcUser oidcUser) {
        Long userId = userDetailsHelperService.processAuthenticatedUser(oidcUser);
        handlerService.handleCartItemBatchDelete(userId);
    }

}
