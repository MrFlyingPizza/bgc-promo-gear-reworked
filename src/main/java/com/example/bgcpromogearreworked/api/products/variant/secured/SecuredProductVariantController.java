package com.example.bgcpromogearreworked.api.products.variant.secured;

import com.example.bgcpromogearreworked.api.products.exceptions.ProductNotFoundException;
import com.example.bgcpromogearreworked.api.products.exceptions.ProductVariantNotFoundException;
import com.example.bgcpromogearreworked.api.products.product.ProductService;
import com.example.bgcpromogearreworked.api.products.variant.ProductVariantService;
import com.example.bgcpromogearreworked.api.products.variant.secured.dto.*;
import com.example.bgcpromogearreworked.persistence.entities.ProductVariant;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Valid
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/secured/products/{productId}/variants", produces = MediaType.APPLICATION_JSON_VALUE)
public class SecuredProductVariantController {

    private final SecuredProductVariantHandlerService handlerService;
    private final ProductService productService;
    private final ProductVariantService variantService;
    private final SecuredProductVariantMapper mapper;

    @PostMapping
    private SecuredProductVariantResponse createProductVariant(@PathVariable Long productId,
                                                               @RequestBody SecuredProductVariantCreate productVariantCreate) {
        if (!productService.checkProductExists(productId)) {
            throw new ProductNotFoundException();
        }
        productVariantCreate.setProductId(productId);
        ProductVariant result = handlerService.handleProductVariantCreate(productVariantCreate);
        return mapper.toResponse(result);
    }

    @GetMapping
    private SecuredProductVariantBatchResponse getProductVariantBatch(@PathVariable Long productId) {
        if (!productService.checkProductExists(productId)) {
            throw new ProductNotFoundException();
        }
        List<ProductVariant> result = handlerService.handleProductVariantBatchGet(productId);
        return mapper.toBatchResponse(result);
    }

    @GetMapping("/{variantId}")
    private SecuredProductVariantResponse getProductVariant(@PathVariable Long productId,
                                                            @PathVariable Long variantId) {
        if (!productService.checkProductExists(productId)) {
            throw new ProductNotFoundException();
        }
        if (!variantService.checkProductVariantExistsOnProduct(productId, variantId)) {
            throw new ProductVariantNotFoundException();
        }
        ProductVariant result = handlerService.handleProductVariantGet(variantId);
        return mapper.toResponse(result);
    }

    @PatchMapping("/{variantId}")
    private SecuredProductVariantResponse updateProductVariantPartial(@PathVariable Long productId,
                                                                      @PathVariable Long variantId,
                                                                      @RequestBody SecuredProductVariantPartialUpdate productVariantPartialUpdate) {
        if (!productService.checkProductExists(productId)) {
            throw new ProductNotFoundException();
        }
        if (!variantService.checkProductVariantExistsOnProduct(productId, variantId)) {
            throw new ProductVariantNotFoundException();
        }
        productVariantPartialUpdate.setProductId(productId);
        productVariantPartialUpdate.setId(variantId);
        return mapper.toResponse(handlerService.handleProductVariantPartialUpdate(productVariantPartialUpdate));
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
        ProductVariant result = handlerService.handleProductVariantUpdate(productVariantUpdate);
        return mapper.toResponse(result);
    }

}
