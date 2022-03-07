package com.example.bgcpromogearreworked.api.products.variant.dto.secured;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
public class ProductVariantCreate {

    @Getter
    private static class NestedOptionValue {

        @JsonIgnore
        @Setter
        private Long productId;

        @NotNull
        private final String name;

        @NotNull
        private final String value;

        NestedOptionValue(String name, String value) {
            this.name = name;
            this.value = value;
        }
    }

    @NotNull
    private Long productId;

    @NotNull
    private final Long imageId;

    @NotNull
    @Min(0)
    private final Integer waitListThreshold;

    @UniqueElements
    private final List<@Valid NestedOptionValue> options;

    @JsonCreator
    private ProductVariantCreate(@JsonProperty Long imageId,
                                 @JsonProperty Integer waitListThreshold,
                                 @JsonProperty List<NestedOptionValue> options) {
        this.imageId = imageId;
        this.waitListThreshold = waitListThreshold;
        this.options = options;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
        this.options.forEach(nestedOptionValue -> nestedOptionValue.setProductId(productId));
    }
}
