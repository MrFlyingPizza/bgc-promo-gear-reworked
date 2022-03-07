package com.example.bgcpromogearreworked.api.products.variant.dto.secured;

import com.example.bgcpromogearreworked.api.products.persistence.ProductVariant;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.data.util.Streamable;

@Mapper(componentModel = "spring")
public interface ProductVariantMapper {

    ProductVariant fromCreate(Long productId, ProductVariantCreate productVariantCreate);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ProductVariant fromUpdate(ProductVariantUpdate productVariantUpdate,
                              @MappingTarget ProductVariant productVariant);

    default ProductVariantResponse toResponse(ProductVariant productVariant) {
        return new ProductVariantResponse(productVariant);
    }

    default ProductVariantBatchResponse toBatchResponse(Iterable<ProductVariant> productVariants) {
        return new ProductVariantBatchResponse(Streamable.of(productVariants).toList());
    }

}
