package com.example.bgcpromogearreworked.api.products.variant.general;

import com.example.bgcpromogearreworked.api.products.variant.ProductVariantService;
import com.example.bgcpromogearreworked.api.products.variant.general.dto.GeneralProductVariantMapper;
import com.example.bgcpromogearreworked.persistence.entities.ProductVariant;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class GeneralProductVariantHandlerService {

    private final GeneralProductVariantMapper mapper;
    private final ProductVariantService variantService;

    ProductVariant handleProductVariantGet(Long variantId) {
        return variantService.getProductVariant(variantId);
    }

    Streamable<ProductVariant> handleProductVariantBatchGet(Long productId) {
        return variantService.getProductVariants(productId).filter(ProductVariant::getIsValid); // only return valid variants to user
    }

}
