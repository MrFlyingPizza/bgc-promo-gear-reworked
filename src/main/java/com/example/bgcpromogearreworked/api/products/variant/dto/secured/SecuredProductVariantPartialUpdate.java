package com.example.bgcpromogearreworked.api.products.variant.dto.secured;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.UniqueElements;

import java.util.List;

@Getter
public class SecuredProductVariantPartialUpdate {

    @JsonIgnore
    @Setter
    private Long id;

    @JsonIgnore
    @Setter
    private Long productId;

    // TODO: 2022-03-10 valid image constraint
    private final Long imageId;

    @Range
    private final Integer waitListThreshold;

    private final @UniqueElements List<Long> optionValueIds;

    @JsonCreator
    public SecuredProductVariantPartialUpdate(@JsonProperty Long imageId,
                                              @JsonProperty Integer waitListThreshold,
                                              @JsonProperty List<Long> optionValueIds) {
        this.imageId = imageId;
        this.waitListThreshold = waitListThreshold;
        this.optionValueIds = optionValueIds;
    }
}
