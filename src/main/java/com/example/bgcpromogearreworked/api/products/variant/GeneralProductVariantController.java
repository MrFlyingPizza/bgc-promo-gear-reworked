package com.example.bgcpromogearreworked.api.products.variant;

import com.example.bgcpromogearreworked.api.products.exceptions.ProductNotFoundException;
import com.example.bgcpromogearreworked.api.products.exceptions.ProductVariantNotFoundException;
import com.example.bgcpromogearreworked.api.products.product.ProductService;
import com.example.bgcpromogearreworked.api.products.variant.dto.general.GeneralProductVariantBatchResponse;
import com.example.bgcpromogearreworked.api.products.variant.dto.general.GeneralProductVariantMapper;
import com.example.bgcpromogearreworked.api.products.variant.dto.general.GeneralProductVariantResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/products/{productId}/variants", produces = MediaType.APPLICATION_JSON_VALUE)
public class GeneralProductVariantController {

    private final ProductVariantService productVariantService;
    private final ProductService productService;
    private final GeneralProductVariantMapper mapper;

    @GetMapping("/{variantId}")
    private GeneralProductVariantResponse getProductVariant(@PathVariable Long productId, @PathVariable Long variantId) {
        if (!productService.checkProductExists(productId)) {
            throw new ProductNotFoundException();
        }
        if (!productVariantService.checkProductVariantExists(productId, variantId)) {
            throw new ProductVariantNotFoundException();
        }
        return mapper.toResponse(productVariantService.handleProductVariantGet(productId));
    }

    @GetMapping
    private GeneralProductVariantBatchResponse getProductVariantBatch(@PathVariable Long productId) {
        if (!productService.checkProductExists(productId)) {
            throw new ProductNotFoundException();
        }
        return mapper.toBatchResponse(productVariantService.handleProductVariantBatchGet(productId));
    }

}
