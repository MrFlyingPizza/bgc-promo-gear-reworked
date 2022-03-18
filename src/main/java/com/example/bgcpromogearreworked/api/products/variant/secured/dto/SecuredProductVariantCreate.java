package com.example.bgcpromogearreworked.api.products.variant.secured.dto;

import com.example.bgcpromogearreworked.api.products.variant.secured.dto.validation.expectedoptioncount.ExpectedOptionCount;
import com.example.bgcpromogearreworked.api.products.variant.secured.dto.validation.expectedoptions.ExpectedOptions;
import com.example.bgcpromogearreworked.api.products.variant.secured.dto.validation.uniqueoptionset.UniqueOptionSet;
import com.example.bgcpromogearreworked.api.shared.validation.constraints.optionvalueexists.OptionValueExists;
import com.example.bgcpromogearreworked.api.shared.validation.constraints.productimageexists.ProductImageExists;
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
@ExpectedOptionCount
@ExpectedOptions(groups = FirstValidationGroup.class)
@UniqueOptionSet(groups = SecondValidationGroup.class)
public class SecuredProductVariantCreate {

    @JsonIgnore
    @Setter
    private Long productId;

    @ProductImageExists
    private final Long imageId;

    @NotNull
    @Range
    private final Integer waitListThreshold;

    @NotNull
    @UniqueElements
    private final List<@NotNull @OptionValueExists Long> optionValueIds;

    @JsonCreator
    SecuredProductVariantCreate(@JsonProperty Long imageId,
                                @JsonProperty Integer waitListThreshold,
                                @JsonProperty List<Long> optionValueIds) {
        this.imageId = imageId;
        this.waitListThreshold = waitListThreshold;
        this.optionValueIds = optionValueIds;
    }
}
