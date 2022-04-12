package ca.bgcengineering.promogearreworked.api.products.variant.secured;

import ca.bgcengineering.promogearreworked.api.products.variant.ProductVariantService;
import ca.bgcengineering.promogearreworked.api.products.variant.secured.dto.SecuredProductVariantMapper;
import ca.bgcengineering.promogearreworked.api.products.variant.secured.dto.SecuredProductVariantPartialUpdate;
import ca.bgcengineering.promogearreworked.api.products.variant.secured.dto.SecuredProductVariantUpdate;
import ca.bgcengineering.promogearreworked.persistence.entities.ProductVariant;
import ca.bgcengineering.promogearreworked.api.products.variant.secured.dto.SecuredProductVariantCreate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

@Service
@Validated
@RequiredArgsConstructor
public class SecuredProductVariantHandlerService {

    private final SecuredProductVariantMapper mapper;
    private final ProductVariantService service;

    ProductVariant handleProductVariantCreate(@Valid SecuredProductVariantCreate productVariantCreate) {
        return service.createProductVariant(productVariantCreate, mapper::fromCreate);
    }

    ProductVariant handleProductVariantGet(Long variantId) {
        return service.getProductVariant(variantId);
    }

    List<ProductVariant> handleProductVariantBatchGet(Long productId) {
        return service.getProductVariants(productId);
    }

    ProductVariant handleProductVariantPartialUpdate(@Valid SecuredProductVariantPartialUpdate productVariantPartialUpdate) {
        return service.updateProductVariant(productVariantPartialUpdate.getId(), productVariantPartialUpdate, mapper::fromPartialUpdate);
    }

    ProductVariant handleProductVariantUpdate(@Valid SecuredProductVariantUpdate productVariantUpdate) {
        return service.updateProductVariant(productVariantUpdate.getId(), productVariantUpdate, mapper::fromUpdate);
    }

}
