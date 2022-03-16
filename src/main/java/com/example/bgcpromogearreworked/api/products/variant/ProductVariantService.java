package com.example.bgcpromogearreworked.api.products.variant;

import com.example.bgcpromogearreworked.api.products.variant.dto.secured.SecuredProductVariantPartialUpdate;
import com.example.bgcpromogearreworked.persistence.entities.ProductVariant;
import com.example.bgcpromogearreworked.persistence.repositories.ProductVariantRepository;
import com.example.bgcpromogearreworked.api.products.exceptions.ProductVariantNotFoundException;
import com.example.bgcpromogearreworked.api.products.variant.dto.secured.SecuredProductVariantCreate;
import com.example.bgcpromogearreworked.api.products.variant.dto.secured.SecuredProductVariantMapper;
import com.example.bgcpromogearreworked.api.products.variant.dto.secured.SecuredProductVariantUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Service
@Validated
@RequiredArgsConstructor
public class ProductVariantService { // TODO: 2022-03-06 finish
    // TODO: 2022-03-06 validation: user is allowed to have a single variant with no options, or multiple variants that have value for every option specified by the product

    private final ProductVariantRepository variantRepo;
    private final SecuredProductVariantMapper mapper;

    public boolean checkProductVariantExists(long productId, long variantId) {
        return variantRepo.existsByIdAndProductId(variantId, productId);
    }

    ProductVariant handleProductVariantCreate(@Valid SecuredProductVariantCreate productVariantCreate) {
        ProductVariant productVariant = mapper.fromCreate(productVariantCreate);

        return variantRepo.saveAndFlush(productVariant);
    }

    ProductVariant handleProductVariantGet(Long variantId) throws ProductVariantNotFoundException {
        return variantRepo.findById(variantId).orElseThrow(ProductVariantNotFoundException::new);
    }

    Iterable<ProductVariant> handleProductVariantBatchGet(Long productId) {
        return variantRepo.findAllByProductId(productId);
    }

    ProductVariant handleProductVariantPartialUpdate(@Valid SecuredProductVariantPartialUpdate productVariantPartialUpdate) {
        ProductVariant productVariant = variantRepo.findById(productVariantPartialUpdate.getId())
                .orElseThrow(ProductVariantNotFoundException::new);
        productVariant = mapper.fromPartialUpdate(productVariantPartialUpdate, productVariant);
        return variantRepo.saveAndFlush(productVariant);
    }

    ProductVariant handleProductVariantUpdate(@Valid SecuredProductVariantUpdate productVariantUpdate) throws ProductVariantNotFoundException {
        ProductVariant productVariant = variantRepo.findById(productVariantUpdate.getId())
                .orElseThrow(ProductVariantNotFoundException::new);
        productVariant = mapper.fromUpdate(productVariantUpdate, productVariant);
        return variantRepo.saveAndFlush(productVariant);
    }

}
