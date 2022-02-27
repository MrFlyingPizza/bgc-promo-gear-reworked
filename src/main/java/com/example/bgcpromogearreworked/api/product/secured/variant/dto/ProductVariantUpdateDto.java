package com.example.bgcpromogearreworked.api.product.secured.variant.dto;

import com.example.bgcpromogearreworked.api.validation.OptionValueExists;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.hibernate.validator.constraints.UniqueElements;

import javax.validation.constraints.Min;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ProductVariantUpdateDto {

    @Getter
    private static class OptionValueDto {
        @JsonIgnore
        private final Long productId;

        private final String name;
        private final String value;

        OptionValueDto(Long productId, String name, String value) {
            this.productId = productId;
            this.name = name;
            this.value = value;
        }
    }

    private final Long productId;

    private final Long imageId;

    @Min(0)
    private final Integer waitListThreshold;

    @UniqueElements
    @OptionValueExists
    private final List<OptionValueDto> options;

    @JsonCreator
    private ProductVariantUpdateDto(@JsonProperty Long productId,
                                    @JsonProperty Long imageId,
                                    @JsonProperty Integer waitListThreshold,
                                    @JsonProperty List<OptionValueDto> options) {
        this.productId = productId;
        this.imageId = imageId;
        this.waitListThreshold = waitListThreshold;
        this.options = options.stream().map(optionValueDto -> new OptionValueDto(productId,
                optionValueDto.name,
                optionValueDto.value)).collect(Collectors.toList());
    }

}
