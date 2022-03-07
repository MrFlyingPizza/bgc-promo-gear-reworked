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
    private ProductVariantResponse createProductVariant(@PathVariable Long productId,
                                                        @RequestBody ProductVariantCreate productVariantCreate) throws ProductNotFoundException {
        if (!productService.checkProductExists(productId)) {
            throw new ProductNotFoundException();
        }
        productVariantCreate.setProductId(productId);
        ProductVariant result = productVariantService.handleProductVariantCreate(productVariantCreate);
        return mapper.toResponse(result);
    }

    @GetMapping
    private ProductVariantBatchResponse getMultipleProductVariants(@PathVariable Long productId) throws ProductNotFoundException {
        if (!productService.checkProductExists(productId)) {
            throw new ProductNotFoundException();
        }
        Iterable<ProductVariant> result = productVariantService.handleProductVariantGetMultiple();
        return mapper.toBatchResponse(result);
    }

    @GetMapping("/{variantId}")
    private ProductVariantResponse getProductVariant(@PathVariable Long productId,
                                                     @PathVariable Long variantId) throws ProductNotFoundException, ProductVariantNotFoundException {
        if (!productService.checkProductExists(productId)) {
            throw new ProductNotFoundException();
        }
        if (productVariantService.checkProductVariantExists(productId, variantId)) {
            throw new ProductVariantNotFoundException();
        }
        ProductVariant result = productVariantService.handleProductVariantGet(variantId);
        return mapper.toResponse(result);
    }

    @PutMapping("/{variantId}")
    private ProductVariantResponse updateProductVariant(@PathVariable Long productId,
                                                        @PathVariable Long variantId,
                                                        @RequestBody ProductVariantUpdate productVariantUpdate) throws ProductNotFoundException, ProductVariantNotFoundException {
        if (!productService.checkProductExists(productId)) {
            throw new ProductNotFoundException();
        }
        productVariantUpdate.setId(variantId);
        productVariantUpdate.setProductId(productId);
        ProductVariant result = productVariantService.handleProductVariantUpdate(productVariantUpdate);
        return mapper.toResponse(result);
    }

}
