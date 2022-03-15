package com.example.bgcpromogearreworked.api.products.variant.dto.secured;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.UniqueElements;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
public class SecuredProductVariantUpdate {

    @JsonIgnore
    @Setter
    private Long id;

    @JsonIgnore
    @Setter
    private Long productId;

    // TODO: 2022-03-10 valid image constraint
    private final Long imageId;

    @NotNull
    @Range
    private final Integer waitListThreshold;

    private final @NotNull @UniqueElements List<Long> optionValueIds;

    @JsonCreator
    SecuredProductVariantUpdate(@JsonProperty Long imageId,
                                @JsonProperty Integer waitListThreshold,
                                @JsonProperty List<Long> optionValueIds) {
        this.imageId = imageId;
        this.waitListThreshold = waitListThreshold;
        this.optionValueIds = optionValueIds;
    }
}
