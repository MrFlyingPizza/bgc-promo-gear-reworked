package com.example.bgcpromogearreworked.persistence.repositories;

import com.example.bgcpromogearreworked.persistence.entities.CartItem;
import com.example.bgcpromogearreworked.persistence.entities.CartItemId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, CartItemId>, QuerydslPredicateExecutor<CartItem> {

    List<CartItem> findAllByUserId(Long userId);
    void deleteAllByUserId(Long userId);

}
