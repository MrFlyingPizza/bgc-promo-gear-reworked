package com.example.bgcpromogearreworked.api.products.product;

import com.example.bgcpromogearreworked.api.products.persistence.Product;
import com.example.bgcpromogearreworked.api.products.exceptions.ProductNotFoundException;
import com.example.bgcpromogearreworked.api.products.product.dto.secured.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/secured/products", produces = MediaType.APPLICATION_JSON_VALUE)
public class SecuredProductController {

    private final ProductService productService;
    private final SecuredProductMapper mapper;

    @PostMapping
    private ProductResponse createProduct(@RequestBody ProductCreate productCreate) {
        Product result = productService.handleProductCreate(productCreate);
        return mapper.toResponse(result);
    }

    @GetMapping("/{productId}")
    private ProductResponse getProduct(@PathVariable Long productId) throws ProductNotFoundException {
        if (!productService.checkProductExists(productId)) {
            throw new ProductNotFoundException();
        }
        Product result = productService.handleProductGet(productId);
        return mapper.toResponse(result);
    }

    @GetMapping("")
    private ProductBatchResponse getProductBatch() {
        Iterable<Product> result = productService.handleProductBatchGet();
        return mapper.toBatchResponse(result);
    }

    @PutMapping("/{productId}")
    private ProductResponse updateProduct(@PathVariable Long productId,
                                          @RequestBody ProductUpdate productUpdate) throws ProductNotFoundException {
        if (!productService.checkProductExists(productId)) {
            throw new ProductNotFoundException();
        }
        productUpdate.setId(productId);
        Product result = productService.handleProductUpdate(productUpdate);
        return mapper.toResponse(result);
    }



}
