package com.example.bgcpromogearreworked.api.product.secured.variant;

import com.example.bgcpromogearreworked.api.product.ProductVariant;
import com.example.bgcpromogearreworked.api.product.ProductVariantRepository;
import com.example.bgcpromogearreworked.api.product.exceptions.ProductVariantNotFoundException;
import com.example.bgcpromogearreworked.api.product.secured.variant.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SecuredProductVariantService {

    private final ProductVariantRepository variantRepo;
    private final ProductVariantMapper mapper;

    public boolean doesProductVariantExist(long productId, long variantId) {
        return variantRepo.existsByIdAndProductId(variantId, productId);
    }

    ProductVariantResponseDto handleProductVariantCreate(Long productId,
                                                         ProductVariantCreateDto productVariantCreateDto) {
        ProductVariant productVariant = mapper.productVariantCreateDtoToProductVariant(productId, productVariantCreateDto);
        productVariant = variantRepo.save(productVariant);
        return mapper.productVariantToProductVariantResponseDto(productVariant);
    }

    ProductVariantResponseDto handleProductVariantGet(Long productId) throws ProductVariantNotFoundException {
        ProductVariant productVariant = variantRepo.findById(productId).orElseThrow(ProductVariantNotFoundException::new);
        return mapper.productVariantToProductVariantResponseDto(productVariant);
    }

    ProductVariantsResponseDto handleProductVariantGetMultiple() {
        return mapper.productVariantsToProductVariantsResponseDto(variantRepo.findAll());
    }

    ProductVariantResponseDto handleProductVariantUpdate(Long productId, ProductVariantUpdateDto productVariantUpdateDto) throws ProductVariantNotFoundException {
        ProductVariant productVariant = variantRepo.findById(productId).orElseThrow(ProductVariantNotFoundException::new);
        productVariant = mapper.productVariantUpdateDtoToProductVariant(productVariantUpdateDto, productVariant);
        return mapper.productVariantToProductVariantResponseDto(productVariant);
    }

}
