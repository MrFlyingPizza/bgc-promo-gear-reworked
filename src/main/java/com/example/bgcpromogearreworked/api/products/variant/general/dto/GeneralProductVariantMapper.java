package com.example.bgcpromogearreworked.api.products.variant.general.dto;

import com.example.bgcpromogearreworked.persistence.entities.OptionValue;
import com.example.bgcpromogearreworked.persistence.entities.ProductVariant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.util.Streamable;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class GeneralProductVariantMapper {

    @Mapping(source = "optionValues", target = "options")
    public abstract GeneralProductVariantResponse toResponse(ProductVariant productVariant);

    public GeneralProductVariantBatchResponse toBatchResponse(List<ProductVariant> productVariants) {
        return new GeneralProductVariantBatchResponse(productVariants.stream().map(this::toResponse).collect(Collectors.toList()));
    }

    @Mapping(source = "option.id", target = "optionId")
    @Mapping(source = "option.name", target = "name")
    protected abstract GeneralProductVariantResponse.NestedOptionValue map(OptionValue optionValue);

}
