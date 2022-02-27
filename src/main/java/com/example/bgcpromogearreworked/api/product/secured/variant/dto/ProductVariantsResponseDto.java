package com.example.bgcpromogearreworked.api.product.secured.variant.dto;

import com.example.bgcpromogearreworked.api.product.ProductVariant;
import lombok.Getter;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ProductVariantsResponseDto {
    private final List<ProductVariantResponseDto> variants;

    ProductVariantsResponseDto(Collection<ProductVariant> productVariants) {
        this.variants = productVariants.stream().map(ProductVariantResponseDto::new).collect(Collectors.toList());
    }
}
