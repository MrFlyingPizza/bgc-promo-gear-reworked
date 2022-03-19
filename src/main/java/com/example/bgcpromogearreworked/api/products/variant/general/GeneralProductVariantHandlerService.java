package com.example.bgcpromogearreworked.api.products.variant.general;

import com.example.bgcpromogearreworked.api.products.variant.ProductVariantService;
import com.example.bgcpromogearreworked.api.products.variant.general.dto.GeneralProductVariantMapper;
import com.example.bgcpromogearreworked.persistence.entities.ProductVariant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
class GeneralProductVariantHandlerService {

    private final GeneralProductVariantMapper mapper;
    private final ProductVariantService variantService;

    ProductVariant handleProductVariantGet(Long variantId) {
        return variantService.getProductVariant(variantId);
    }

    List<ProductVariant> handleProductVariantBatchGet(Long productId) {
        return variantService.getProductVariants(productId).stream().filter(ProductVariant::getIsInUse).collect(Collectors.toList()); // only return valid variants to user
    }

}
