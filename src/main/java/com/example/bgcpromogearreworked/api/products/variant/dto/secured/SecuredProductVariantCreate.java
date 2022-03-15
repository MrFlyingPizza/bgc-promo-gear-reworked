package com.example.bgcpromogearreworked.api.products.variant.dto.secured;

import com.example.bgcpromogearreworked.api.products.variant.dto.secured.validation.uniqueoptionset.UniqueOptionSet;
import com.example.bgcpromogearreworked.api.shared.validation.groups.FirstValidationGroup;
import com.example.bgcpromogearreworked.api.shared.validation.groups.SecondValidationGroup;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.UniqueElements;

import javax.validation.GroupSequence;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@GroupSequence({SecuredProductVariantCreate.class, FirstValidationGroup.class, SecondValidationGroup.class})
@UniqueOptionSet
public class SecuredProductVariantCreate {

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
    SecuredProductVariantCreate(@JsonProperty Long imageId,
                                @JsonProperty Integer waitListThreshold,
                                @JsonProperty List<Long> optionValueIds) {
        this.imageId = imageId;
        this.waitListThreshold = waitListThreshold;
        this.optionValueIds = optionValueIds;
    }
}
