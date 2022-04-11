package ca.bgcengineering.promogearreworked.persistence.repositories;

import ca.bgcengineering.promogearreworked.persistence.entities.CartItem;
import ca.bgcengineering.promogearreworked.persistence.entities.CartItemId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, CartItemId>, QuerydslPredicateExecutor<CartItem> {

    List<CartItem> findAllByUserId(Long userId);
    void deleteAllByUserId(Long userId);

}
