package com.example.bgcpromogearreworked.api.products.variant;

import com.example.bgcpromogearreworked.api.products.persistence.ProductVariant;
import com.example.bgcpromogearreworked.api.products.persistence.ProductVariantRepository;
import com.example.bgcpromogearreworked.api.products.exceptions.ProductVariantNotFoundException;
import com.example.bgcpromogearreworked.api.products.variant.dto.secured.ProductVariantCreate;
import com.example.bgcpromogearreworked.api.products.variant.dto.secured.ProductVariantMapper;
import com.example.bgcpromogearreworked.api.products.variant.dto.secured.ProductVariantUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Service
@Validated
@RequiredArgsConstructor
public class ProductVariantService {

    private final ProductVariantRepository variantRepo;
    private final ProductVariantMapper mapper;

    public boolean checkProductVariantExists(long productId, long variantId) {
        return variantRepo.existsByIdAndProductId(variantId, productId);
    }

    ProductVariant handleProductVariantCreate(@Valid ProductVariantCreate productVariantCreate) {
        ProductVariant productVariant = mapper.fromCreate(productVariantCreate.getProductId(), productVariantCreate);

        return variantRepo.saveAndFlush(productVariant);
    }

    ProductVariant handleProductVariantGet(Long productId) throws ProductVariantNotFoundException {
        return variantRepo.findById(productId).orElseThrow(ProductVariantNotFoundException::new);
    }

    Iterable<ProductVariant> handleProductVariantGetMultiple() {
        return variantRepo.findAll();
    }

    ProductVariant handleProductVariantUpdate(@Valid ProductVariantUpdate productVariantUpdate) throws ProductVariantNotFoundException {
        ProductVariant productVariant = variantRepo.findById(productVariantUpdate.getId())
                .orElseThrow(ProductVariantNotFoundException::new);
        productVariant = mapper.fromUpdate(productVariantUpdate, productVariant);
        return variantRepo.saveAndFlush(productVariant);
    }

}
