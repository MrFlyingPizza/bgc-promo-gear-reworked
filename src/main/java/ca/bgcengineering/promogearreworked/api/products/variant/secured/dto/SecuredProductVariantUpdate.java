package ca.bgcengineering.promogearreworked.api.products.variant.secured.dto;

import ca.bgcengineering.promogearreworked.api.products.variant.secured.dto.validation.expectedoptioncount.ExpectedOptionCount;
import ca.bgcengineering.promogearreworked.api.products.variant.secured.dto.validation.expectedoptions.ExpectedOptions;
import ca.bgcengineering.promogearreworked.api.products.variant.secured.dto.validation.uniqueoptionset.UniqueOptionSet;
import ca.bgcengineering.promogearreworked.api.shared.validation.constraints.optionvalueexists.OptionValueExists;
import ca.bgcengineering.promogearreworked.api.shared.validation.constraints.productimageexists.ProductImageExists;
import ca.bgcengineering.promogearreworked.api.shared.validation.groups.FirstValidationGroup;
import ca.bgcengineering.promogearreworked.api.shared.validation.groups.SecondValidationGroup;
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
@GroupSequence({SecuredProductVariantUpdate.class, FirstValidationGroup.class, SecondValidationGroup.class})
@ExpectedOptionCount
@ExpectedOptions(groups = FirstValidationGroup.class)
@UniqueOptionSet(groups = SecondValidationGroup.class)
public class SecuredProductVariantUpdate {

    @JsonIgnore
    @Setter
    private Long id;

    @JsonIgnore
    @Setter
    private Long productId;

    @ProductImageExists
    private final Long imageId;

    private final Boolean isInUse;

    @NotNull
    @Range
    private final Integer waitListThreshold;

    @NotNull
    @UniqueElements
    private final List<@NotNull @OptionValueExists Long> optionValueIds;

    @JsonCreator
    public SecuredProductVariantUpdate(@JsonProperty("imageId") Long imageId,
                                @JsonProperty("waitListThreshold") Integer waitListThreshold,
                                @JsonProperty("isInUse") Boolean isInUse,
                                @JsonProperty("optionValueIds") List<Long> optionValueIds) {
        this.imageId = imageId;
        this.isInUse = isInUse;
        this.waitListThreshold = waitListThreshold;
        this.optionValueIds = optionValueIds;
    }
}
