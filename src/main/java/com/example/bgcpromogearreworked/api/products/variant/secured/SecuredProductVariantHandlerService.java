package com.example.bgcpromogearreworked.api.products.variant.secured;

import com.example.bgcpromogearreworked.api.products.exceptions.ProductVariantNotFoundException;
import com.example.bgcpromogearreworked.api.products.variant.ProductVariantService;
import com.example.bgcpromogearreworked.api.products.variant.secured.dto.SecuredProductVariantCreate;
import com.example.bgcpromogearreworked.api.products.variant.secured.dto.SecuredProductVariantMapper;
import com.example.bgcpromogearreworked.api.products.variant.secured.dto.SecuredProductVariantPartialUpdate;
import com.example.bgcpromogearreworked.api.products.variant.secured.dto.SecuredProductVariantUpdate;
import com.example.bgcpromogearreworked.persistence.entities.ProductVariant;
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

    ProductVariant handleProductVariantGet(Long variantId) throws ProductVariantNotFoundException {
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
