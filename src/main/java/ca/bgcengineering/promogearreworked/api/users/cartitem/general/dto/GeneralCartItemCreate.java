package ca.bgcengineering.promogearreworked.api.users.cartitem.general.dto;

import ca.bgcengineering.promogearreworked.api.shared.validation.constraints.productvariantinuse.InUseProductVariantExists;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
public class GeneralCartItemCreate {

    @NotNull
    @InUseProductVariantExists
    private final Long variantId;

    @JsonIgnore
    @Setter
    private Long userId;

    @Min(1)
    @NotNull
    private final Integer quantity;

    @JsonCreator
    public GeneralCartItemCreate(@JsonProperty("variantId") Long variantId,
                                 @JsonProperty("quantity") Integer quantity) {
        this.variantId = variantId;
        this.quantity = quantity;
    }
}
