package ca.bgcengineering.promogearreworked.api.users.cartitem.general;

import ca.bgcengineering.promogearreworked.api.users.cartitem.general.dto.GeneralCartItemCreate;
import ca.bgcengineering.promogearreworked.api.users.cartitem.general.dto.GeneralCartItemMapper;
import ca.bgcengineering.promogearreworked.api.users.cartitem.general.dto.GeneralCartItemUpdate;
import ca.bgcengineering.promogearreworked.api.users.cartitem.CartItemService;
import ca.bgcengineering.promogearreworked.persistence.entities.CartItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

@Service
@Validated
@RequiredArgsConstructor
public class GeneralCartItemHandlerService {

    private final CartItemService cartItemService;
    private final GeneralCartItemMapper mapper;

    CartItem handleCartItemCreate(@Valid GeneralCartItemCreate cartItemCreate) {
        return cartItemService.createCartItem(cartItemCreate, mapper::fromCreate);
    }

    CartItem handleCartItemUpdate(@Valid GeneralCartItemUpdate cartItemUpdate) {
        return cartItemService.updateCartItem(cartItemUpdate.getUserId(),
                cartItemUpdate.getVariantId(),
                cartItemUpdate,
                mapper::fromUpdate);
    }

    CartItem handleCartItemGet(Long userId, Long variantId) {
        return cartItemService.getCartItem(userId, variantId);
    }

    List<CartItem> handleCartItemBatchGet(Long userId) {
        return cartItemService.getCartItems(userId);
    }

    void handleCartItemDelete(Long userId, Long variantId) {
        cartItemService.deleteCartItem(userId, variantId);
    }

    void handleCartItemBatchDelete(Long userId) {
        cartItemService.deleteCartItems(userId);
    }

}
