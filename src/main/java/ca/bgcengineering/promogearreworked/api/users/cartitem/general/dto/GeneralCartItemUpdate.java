package ca.bgcengineering.promogearreworked.api.users.cartitem.general.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
public class GeneralCartItemUpdate {

    @JsonIgnore
    @Setter
    private Long variantId;

    @JsonIgnore
    @Setter
    private Long userId;

    @Min(1)
    @NotNull
    private final Integer quantity;

    @JsonCreator
    public GeneralCartItemUpdate(@JsonProperty("quantity") Integer quantity) {
        this.quantity = quantity;
    }
}
