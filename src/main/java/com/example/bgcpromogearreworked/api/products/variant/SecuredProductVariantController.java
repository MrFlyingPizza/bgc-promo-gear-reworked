package com.example.bgcpromogearreworked.api.products.variant;

import com.example.bgcpromogearreworked.api.products.exceptions.ProductNotFoundException;
import com.example.bgcpromogearreworked.api.products.exceptions.ProductVariantNotFoundException;
import com.example.bgcpromogearreworked.api.products.product.ProductService;
import com.example.bgcpromogearreworked.api.products.variant.dto.secured.*;
import com.example.bgcpromogearreworked.persistence.entities.ProductVariant;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@Valid
@RestController
@RequestMapping(path = "/api/secured/products/{productId}/variants", produces = MediaType.APPLICATION_JSON_VALUE)
public class SecuredProductVariantController {

    private final ProductVariantService productVariantService;
    private final ProductService productService;
    private final SecuredProductVariantMapper mapper;

    @PostMapping
    private SecuredProductVariantResponse createProductVariant(@PathVariable Long productId,
                                                               @RequestBody SecuredProductVariantCreate productVariantCreate) {
        if (!productService.checkProductExists(productId)) {
            throw new ProductNotFoundException();
        }
        productVariantCreate.setProductId(productId);
        ProductVariant result = productVariantService.handleProductVariantCreate(productVariantCreate);
        return mapper.toResponse(result);
    }

    @GetMapping
    private SecuredProductVariantBatchResponse getProductVariantBatch(@PathVariable Long productId) {
        if (!productService.checkProductExists(productId)) {
            throw new ProductNotFoundException();
        }
        Iterable<ProductVariant> result = productVariantService.handleProductVariantBatchGet(productId);
        return mapper.toBatchResponse(result);
    }

    @GetMapping("/{variantId}")
    private SecuredProductVariantResponse getProductVariant(@PathVariable Long productId,
                                                            @PathVariable Long variantId) {
        if (!productService.checkProductExists(productId)) {
            throw new ProductNotFoundException();
        }
        if (productVariantService.checkProductVariantExists(productId, variantId)) {
            throw new ProductVariantNotFoundException();
        }
        ProductVariant result = productVariantService.handleProductVariantGet(variantId);
        return mapper.toResponse(result);
    }

    @PatchMapping("/{variantId}")
    private SecuredProductVariantResponse updateProductVariantPartial(@PathVariable Long productId,
                                                                      @PathVariable Long variantId,
                                                                      @RequestBody SecuredProductVariantPartialUpdate productVariantPartialUpdate) {
        if (!productService.checkProductExists(productId)) {
            throw new ProductNotFoundException();
        }
        if (productVariantService.checkProductVariantExists(productId, variantId)) {
            throw new ProductVariantNotFoundException();
        }
        productVariantPartialUpdate.setProductId(productId);
        productVariantPartialUpdate.setId(variantId);
        return mapper.toResponse(productVariantService.handleProductVariantPartialUpdate(productVariantPartialUpdate));
    }

    @PutMapping("/{variantId}")
    private SecuredProductVariantResponse updateProductVariant(@PathVariable Long productId,
                                                               @PathVariable Long variantId,
                                                               @RequestBody SecuredProductVariantUpdate productVariantUpdate) {
        if (!productService.checkProductExists(productId)) {
            throw new ProductNotFoundException();
        }
        productVariantUpdate.setId(variantId);
        productVariantUpdate.setProductId(productId);
        ProductVariant result = productVariantService.handleProductVariantUpdate(productVariantUpdate);
        return mapper.toResponse(result);
    }

}
