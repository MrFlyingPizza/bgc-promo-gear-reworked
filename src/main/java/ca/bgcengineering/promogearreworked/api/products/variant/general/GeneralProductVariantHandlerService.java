package ca.bgcengineering.promogearreworked.api.products.variant.general;

import ca.bgcengineering.promogearreworked.api.products.variant.ProductVariantService;
import ca.bgcengineering.promogearreworked.persistence.entities.ProductVariant;
import ca.bgcengineering.promogearreworked.api.products.variant.general.dto.GeneralProductVariantMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GeneralProductVariantHandlerService {

    private final GeneralProductVariantMapper mapper;
    private final ProductVariantService variantService;

    ProductVariant handleProductVariantGet(Long variantId) {
        return variantService.getProductVariant(variantId);
    }

    List<ProductVariant> handleProductVariantBatchGet(Long productId) {
        return variantService.getProductVariants(productId).stream().filter(ProductVariant::getIsInUse).collect(Collectors.toList()); // only return valid variants to user
    }

}
