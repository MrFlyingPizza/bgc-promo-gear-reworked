package com.example.bgcpromogearreworked.api.product.secured.variant;

import com.example.bgcpromogearreworked.api.product.ProductVariant;
import com.example.bgcpromogearreworked.api.product.exceptions.ProductNotFoundException;
import com.example.bgcpromogearreworked.api.product.exceptions.ProductVariantNotFoundException;
import com.example.bgcpromogearreworked.api.product.secured.product.SecuredProductService;
import com.example.bgcpromogearreworked.api.product.secured.variant.dto.ProductVariantCreateDto;
import com.example.bgcpromogearreworked.api.product.secured.variant.dto.ProductVariantResponseDto;
import com.example.bgcpromogearreworked.api.product.secured.variant.dto.ProductVariantUpdateDto;
import com.example.bgcpromogearreworked.api.product.secured.variant.dto.ProductVariantsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@Valid
@RestController
@RequestMapping("/api/secured/products/{productId}/variants")
public class SecuredProductVariantController {

    private final SecuredProductVariantService securedProductVariantService;
    private final SecuredProductService securedProductService;

    @PostMapping
    private ProductVariantResponseDto createProductVariant(@PathVariable Long productId,
                                                           @Valid ProductVariantCreateDto productVariantCreateDto) throws ProductNotFoundException {
        if (!securedProductService.doesProductExist(productId)) {
            throw new ProductNotFoundException();
        }
        return securedProductVariantService.handleProductVariantCreate(productId, productVariantCreateDto);
    }

    @GetMapping
    private ProductVariantsResponseDto getMultipleProductVariants(@PathVariable Long productId,
                                                                  @Valid ProductVariantUpdateDto productVariantUpdateDto) throws ProductNotFoundException {
        if (!securedProductService.doesProductExist(productId)) {
            throw new ProductNotFoundException();
        }
        return securedProductVariantService.handleProductVariantGetMultiple();
    }

    @GetMapping("/{variantId}")
    private ProductVariantResponseDto getProductVariant(@PathVariable Long productId,
                                                        @PathVariable Long variantId) throws ProductNotFoundException, ProductVariantNotFoundException {
        if (!securedProductService.doesProductExist(productId)) {
            throw new ProductNotFoundException();
        }
        if (securedProductVariantService.doesProductVariantExist(productId, variantId)) {
            throw new ProductVariantNotFoundException();
        }
        return securedProductVariantService.handleProductVariantGet(variantId);
    }

    @PutMapping
    private ProductVariantResponseDto updateProductVariant(@PathVariable Long productId,
                                                           @Valid ProductVariantUpdateDto productVariantUpdateDto) throws ProductNotFoundException, ProductVariantNotFoundException {
        if (!securedProductService.doesProductExist(productId)) {
            throw new ProductNotFoundException();
        }
        return securedProductVariantService.handleProductVariantUpdate(productId, productVariantUpdateDto);
    }

}
