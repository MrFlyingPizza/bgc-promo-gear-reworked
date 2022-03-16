package com.example.bgcpromogearreworked.api.products.variant.dto.general;

import com.example.bgcpromogearreworked.persistence.entities.ProductVariant;
import org.mapstruct.Mapper;
import org.springframework.data.util.Streamable;

@Mapper(componentModel = "spring")
public abstract class GeneralProductVariantMapper {

    public abstract GeneralProductVariantResponse toResponse(ProductVariant productVariant);

    public GeneralProductVariantBatchResponse toBatchResponse(Iterable<ProductVariant> productVariants) {
        return new GeneralProductVariantBatchResponse(Streamable.of(productVariants).map(this::toResponse).toList());
    }

}
