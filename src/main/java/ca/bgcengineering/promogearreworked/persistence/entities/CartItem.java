package ca.bgcengineering.promogearreworked.persistence.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "cart_item", indexes = {
        @Index(name = "user_has_cart_item_user_id_variant_id_uindex", columnList = "user_id, variant_id", unique = true)
})
@Entity
@Getter
@Setter
@IdClass(CartItemId.class)
public class CartItem {

    @Id
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Id
    @Column(name = "variant_id", nullable = false)
    private Long variantId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false, updatable = false, insertable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "variant_id", nullable = false, updatable = false, insertable = false)
    private ProductVariant variant;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;
}