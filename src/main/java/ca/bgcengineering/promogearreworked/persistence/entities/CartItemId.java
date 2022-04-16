package ca.bgcengineering.promogearreworked.persistence.entities;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class CartItemId implements Serializable {

    private Long userId;
    private Long variantId;

}
