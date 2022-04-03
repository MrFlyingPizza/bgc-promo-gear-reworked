package com.example.bgcpromogearreworked.persistence.entities;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Table(name = "order_item")
@Entity
@IdClass(OrderItemId.class)
@Getter
@Setter
public class OrderItem {

    @Id
    @Column(name = "order_id", nullable = false)
    private Long orderId;

    @Id
    @Column(name = "variant_id", nullable = false)
    private Long variantId;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Id
    @ManyToOne
    @JoinColumn(name = "order_id", insertable = false, updatable = false)
    private Order order;

    @Transient
    @Setter(AccessLevel.PRIVATE)
    private BigDecimal cost;

    @PostLoad
    private void calculateCost() {
        this.cost = price.multiply(BigDecimal.valueOf(quantity));
    }

}