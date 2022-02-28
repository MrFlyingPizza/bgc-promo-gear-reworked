package com.example.bgcpromogearreworked.api.products.variant;

import com.example.bgcpromogearreworked.api.products.persistence.ProductVariant;
import com.example.bgcpromogearreworked.api.products.persistence.ProductVariantRepository;
import com.example.bgcpromogearreworked.api.products.exceptions.ProductVariantNotFoundException;
import com.example.bgcpromogearreworked.api.products.variant.dto.secured.ProductVariantCreateDto;
import com.example.bgcpromogearreworked.api.products.variant.dto.secured.ProductVariantMapper;
import com.example.bgcpromogearreworked.api.products.variant.dto.secured.ProductVariantUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductVariantService {

    private final ProductVariantRepository variantRepo;
    private final ProductVariantMapper mapper;

    public boolean checkProductVariantExists(long productId, long variantId) {
        return variantRepo.existsByIdAndProductId(variantId, productId);
    }

    ProductVariant handleProductVariantCreate(Long productId,
                                                         ProductVariantCreateDto productVariantCreateDto) {
        ProductVariant productVariant = mapper.productVariantCreateDtoToProductVariant(productId, productVariantCreateDto);
        return variantRepo.save(productVariant);
    }

    ProductVariant handleProductVariantGet(Long productId) throws ProductVariantNotFoundException {
        return variantRepo.findById(productId).orElseThrow(ProductVariantNotFoundException::new);
    }

    Iterable<ProductVariant> handleProductVariantGetMultiple() {
        return variantRepo.findAll();
    }

    ProductVariant handleProductVariantUpdate(Long productId, ProductVariantUpdateDto productVariantUpdateDto) throws ProductVariantNotFoundException {
        ProductVariant productVariant = variantRepo.findById(productId).orElseThrow(ProductVariantNotFoundException::new);
        productVariant = mapper.productVariantUpdateDtoToProductVariant(productVariantUpdateDto, productVariant);
        return variantRepo.save(productVariant);
    }

}
