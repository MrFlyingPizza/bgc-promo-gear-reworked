package ca.bgcengineering.promogearreworked.persistence.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
public class OrderItemId implements Serializable {
    private Long orderId;
    private Long variantId;

}