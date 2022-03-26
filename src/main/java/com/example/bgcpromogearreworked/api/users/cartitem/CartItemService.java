package com.example.bgcpromogearreworked.api.users.cartitem;

import com.example.bgcpromogearreworked.api.users.exceptions.UserCartItemNotFoundException;
import com.example.bgcpromogearreworked.persistence.entities.CartItem;
import com.example.bgcpromogearreworked.persistence.entities.CartItemId;
import com.example.bgcpromogearreworked.persistence.repositories.CartItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class CartItemService {

    private final CartItemRepository cartItemRepo;

    private static CartItemId buildId(Long userId, Long variantId) {
        CartItemId id = new CartItemId();
        id.setUserId(userId);
        id.setVariantId(variantId);
        return id;
    }

    public boolean checkCartItemExists(Long userId, Long variantId) {
        return cartItemRepo.existsById(buildId(userId, variantId));
    }

    public List<CartItem> getCartItems(Long userId) {
        assert userId != null;
        return cartItemRepo.findAllByUserId(userId);
    }

    public CartItem getCartItem(Long userId, Long variantId) {
        assert userId != null && variantId != null;
        return cartItemRepo.findById(buildId(userId, variantId)).orElseThrow(UserCartItemNotFoundException::new);
    }

    public <T> CartItem createCartItem(T source, Function<T, CartItem> mapper) {
        assert source != null && mapper != null;
        return cartItemRepo.save(mapper.apply(source));
    }

    public <T> CartItem updateCartItem(Long userId, Long variantId, T source, BiFunction<T, CartItem, CartItem> mapper) {
        assert userId != null && variantId != null && source != null && mapper != null;
        CartItem cartItem = mapper.apply(source, cartItemRepo.findById(buildId(userId, variantId)).orElseThrow(UserCartItemNotFoundException::new));
        assert userId.equals(cartItem.getUser().getId()) && variantId.equals(cartItem.getVariant().getId());
        return cartItemRepo.save(cartItem);
    }

    public void deleteCartItem(Long userId, Long variantId) {
        assert userId != null && variantId != null;
        cartItemRepo.deleteById(buildId(userId, variantId));
    }

    public void deleteCartItems(Long userId) {
        assert userId != null;
        cartItemRepo.deleteAllByUserId(userId);
    }

}
