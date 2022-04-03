package com.example.bgcpromogearreworked.persistence.repositories;

import com.example.bgcpromogearreworked.persistence.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long>, QuerydslPredicateExecutor<Order> {

    List<Order> findAllBySubmitterId(Long submitterId);

}
