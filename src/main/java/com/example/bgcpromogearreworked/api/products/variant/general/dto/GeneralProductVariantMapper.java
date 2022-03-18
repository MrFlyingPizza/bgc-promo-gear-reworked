package com.example.bgcpromogearreworked.api.products.variant.general.dto;

import com.example.bgcpromogearreworked.persistence.entities.OptionValue;
import com.example.bgcpromogearreworked.persistence.entities.ProductVariant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.util.Streamable;

@Mapper(componentModel = "spring")
public abstract class GeneralProductVariantMapper {

    @Mapping(source = "optionValues", target = "options")
    public abstract GeneralProductVariantResponse toResponse(ProductVariant productVariant);

    public GeneralProductVariantBatchResponse toBatchResponse(Iterable<ProductVariant> productVariants) {
        return new GeneralProductVariantBatchResponse(Streamable.of(productVariants).map(this::toResponse).toList());
    }

    @Mapping(source = "option.id", target = "optionId")
    @Mapping(source = "option.name", target = "name")
    protected abstract GeneralProductVariantResponse.NestedOptionValue map(OptionValue optionValue);

}
