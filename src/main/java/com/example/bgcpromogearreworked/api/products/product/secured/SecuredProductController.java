package com.example.bgcpromogearreworked.api.products.product.secured;

import com.example.bgcpromogearreworked.api.products.product.ProductService;
import com.example.bgcpromogearreworked.api.products.product.secured.dto.*;
import com.example.bgcpromogearreworked.persistence.entities.Product;
import com.example.bgcpromogearreworked.api.products.exceptions.ProductNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Streamable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/secured/products", produces = MediaType.APPLICATION_JSON_VALUE)
public class SecuredProductController {

    private final ProductService productService;
    private final SecuredProductHandlerService handlerService;
    private final SecuredProductMapper mapper;

    @PostMapping
    private SecuredProductResponse createProduct(@RequestBody SecuredProductCreate productCreate) {
        Product result = handlerService.handleProductCreate(productCreate);
        return mapper.toResponse(result);
    }

    @GetMapping("/{productId}")
    private SecuredProductResponse getProduct(@PathVariable Long productId) {
        if (!productService.checkProductExists(productId)) {
            throw new ProductNotFoundException();
        }
        Product result = handlerService.handleProductGet(productId);
        return mapper.toResponse(result);
    }

    @GetMapping
    private SecuredProductBatchResponse getProductBatch() {
        Streamable<Product> result = handlerService.handleProductBatchGet();
        return mapper.toBatchResponse(result);
    }

    @PatchMapping("/{productId}")
    private SecuredProductResponse updateProductPartial(@PathVariable Long productId,
                                                        @RequestBody SecuredProductPartialUpdate productPartialUpdate) {
        if (!productService.checkProductExists(productId)) {
            throw new ProductNotFoundException();
        }
        productPartialUpdate.setId(productId);
        return mapper.toResponse(handlerService.handleProductPartialUpdate(productPartialUpdate));
    }

    @PutMapping("/{productId}")
    private SecuredProductResponse updateProduct(@PathVariable Long productId,
                                                 @RequestBody SecuredProductUpdate productUpdate) {
        if (!productService.checkProductExists(productId)) {
            throw new ProductNotFoundException();
        }
        productUpdate.setId(productId);
        Product result = handlerService.handleProductUpdate(productUpdate);
        return mapper.toResponse(result);
    }



}
