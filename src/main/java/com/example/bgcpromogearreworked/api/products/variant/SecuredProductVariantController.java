package com.example.bgcpromogearreworked.api.products.variant;

import com.example.bgcpromogearreworked.api.products.persistence.ProductVariant;
import com.example.bgcpromogearreworked.api.products.exceptions.ProductNotFoundException;
import com.example.bgcpromogearreworked.api.products.exceptions.ProductVariantNotFoundException;
import com.example.bgcpromogearreworked.api.products.product.ProductService;
import com.example.bgcpromogearreworked.api.products.variant.dto.secured.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@Valid
@RestController
@RequestMapping("/api/secured/products/{productId}/variants")
public class SecuredProductVariantController {

    private final ProductVariantService productVariantService;
    private final ProductService productService;
    private final ProductVariantMapper mapper;

    @PostMapping
    private ProductVariantResponseDto createProductVariant(@PathVariable Long productId,
                                                           @Valid ProductVariantCreateDto productVariantCreateDto) throws ProductNotFoundException {
        if (!productService.checkProductExists(productId)) {
            throw new ProductNotFoundException();
        }
        ProductVariant result = productVariantService.handleProductVariantCreate(productId, productVariantCreateDto);
        return mapper.productVariantToProductVariantResponseDto(result);
    }

    @GetMapping
    private ProductVariantsResponseDto getMultipleProductVariants(@PathVariable Long productId) throws ProductNotFoundException {
        if (!productService.checkProductExists(productId)) {
            throw new ProductNotFoundException();
        }
        Iterable<ProductVariant> result = productVariantService.handleProductVariantGetMultiple();
        return mapper.productVariantsToProductVariantsResponseDto(result);
    }

    @GetMapping("/{variantId}")
    private ProductVariantResponseDto getProductVariant(@PathVariable Long productId,
                                                        @PathVariable Long variantId) throws ProductNotFoundException, ProductVariantNotFoundException {
        if (!productService.checkProductExists(productId)) {
            throw new ProductNotFoundException();
        }
        if (productVariantService.checkProductVariantExists(productId, variantId)) {
            throw new ProductVariantNotFoundException();
        }
        ProductVariant result = productVariantService.handleProductVariantGet(variantId);
        return mapper.productVariantToProductVariantResponseDto(result);
    }

    @PutMapping
    private ProductVariantResponseDto updateProductVariant(@PathVariable Long productId,
                                                           @RequestBody @Valid ProductVariantUpdateDto productVariantUpdateDto) throws ProductNotFoundException, ProductVariantNotFoundException {
        if (!productService.checkProductExists(productId)) {
            throw new ProductNotFoundException();
        }
        ProductVariant result = productVariantService.handleProductVariantUpdate(productId, productVariantUpdateDto);
        return mapper.productVariantToProductVariantResponseDto(result);
    }

}
