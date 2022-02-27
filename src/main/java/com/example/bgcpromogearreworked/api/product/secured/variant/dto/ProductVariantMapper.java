package com.example.bgcpromogearreworked.api.product.secured.variant.dto;

import com.example.bgcpromogearreworked.api.product.ProductVariant;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.data.util.Streamable;

@Mapper(componentModel = "spring")
public interface ProductVariantMapper {

    ProductVariant productVariantCreateDtoToProductVariant(Long productId, ProductVariantCreateDto productVariantCreateDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ProductVariant productVariantUpdateDtoToProductVariant(ProductVariantUpdateDto productVariantUpdateDto,
                                                                      @MappingTarget ProductVariant productVariant);

    default ProductVariantResponseDto productVariantToProductVariantResponseDto(ProductVariant productVariant) {
        return new ProductVariantResponseDto(productVariant);
    }

    default ProductVariantsResponseDto productVariantsToProductVariantsResponseDto(Iterable<ProductVariant> productVariants) {
        return new ProductVariantsResponseDto(Streamable.of(productVariants).toList());
    }

}
