package com.example.bgcpromogearreworked.api.products.product;

import com.example.bgcpromogearreworked.api.products.persistence.Product;
import com.example.bgcpromogearreworked.api.products.exceptions.ProductNotFoundException;
import com.example.bgcpromogearreworked.api.products.product.dto.secured.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/secured/products", produces = MediaType.APPLICATION_JSON_VALUE)
public class SecuredProductController {

    private final ProductService service;
    private final SecuredProductMapper mapper;

    @PostMapping
    private SecuredProductResponse createProduct(@RequestBody SecuredProductCreate productCreate) {
        Product result = service.handleProductCreate(productCreate);
        return mapper.toResponse(result);
    }

    @GetMapping("/{productId}")
    private SecuredProductResponse getProduct(@PathVariable Long productId) throws ProductNotFoundException {
        if (!service.checkProductExists(productId)) {
            throw new ProductNotFoundException();
        }
        Product result = service.handleProductGet(productId);
        return mapper.toResponse(result);
    }

    @GetMapping("")
    private SecuredProductBatchResponse getProductBatch() {
        Iterable<Product> result = service.handleProductBatchGet();
        return mapper.toBatchResponse(result);
    }

    @PatchMapping("/{productId}")
    private SecuredProductResponse updateProductPartial(@PathVariable Long productId,
                                                        @RequestBody SecuredProductPartialUpdate productPartialUpdate) {
        if (!service.checkProductExists(productId)) {
            throw new ProductNotFoundException();
        }
        productPartialUpdate.setId(productId);
        return mapper.toResponse(service.handleProductPartialUpdate(productPartialUpdate));
    }

    @PutMapping("/{productId}")
    private SecuredProductResponse updateProduct(@PathVariable Long productId,
                                                 @RequestBody SecuredProductUpdate productUpdate) throws ProductNotFoundException {
        if (!service.checkProductExists(productId)) {
            throw new ProductNotFoundException();
        }
        productUpdate.setId(productId);
        Product result = service.handleProductUpdate(productUpdate);
        return mapper.toResponse(result);
    }



}
