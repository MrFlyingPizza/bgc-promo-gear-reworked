package com.example.bgcpromogearreworked.api.products.variant.dto.secured;

import com.example.bgcpromogearreworked.api.common.validation.OptionValueExists;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.hibernate.validator.constraints.UniqueElements;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ProductVariantCreateDto {

    @Getter
    @OptionValueExists
    private static class OptionValueDto {

        @JsonIgnore
        private final Long productId;

        @NotNull
        private final String name;

        @NotNull
        private final String value;

        OptionValueDto(Long productId, String name, String value) {
            this.productId = productId;
            this.name = name;
            this.value = value;
        }
    }

    @NotNull
    private final Long productId;

    @NotNull
    private final Long imageId;

    @NotNull
    @Min(0)
    private final Integer waitListThreshold;

    @UniqueElements
    private final List<@Valid OptionValueDto> options;

    @JsonCreator
    private ProductVariantCreateDto(@JsonProperty Long productId,
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
