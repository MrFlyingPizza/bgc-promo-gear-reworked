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
    private ProductResponseDto createProduct(@RequestBody @Valid ProductCreateDto productCreateDto) {
        Product result = productService.handleProductCreate(productCreateDto);
        return mapper.toResponseDto(result);
    }

    @GetMapping("/{productId}")
    private ProductResponseDto getProduct(@PathVariable Long productId) throws ProductNotFoundException {
        if (!productService.checkProductExists(productId)) {
            throw new ProductNotFoundException();
        }
        Product result = productService.handleProductGet(productId);
        return mapper.toResponseDto(result);
    }

    @GetMapping("")
    private ProductBatchResponseDto getProductBatch() {
        Iterable<Product> result = productService.handleProductBatchGet();
        return mapper.toBatchResponseDto(result);
    }

    @PutMapping("/{productId}")
    private ProductResponseDto updateProduct(@PathVariable Long productId,
                                             @RequestBody @Valid ProductUpdateDto productUpdateDto) throws ProductNotFoundException {
        if (!productService.checkProductExists(productId)) {
            throw new ProductNotFoundException();
        }
        Product result = productService.handleProductUpdate(productId, productUpdateDto);
        return mapper.toResponseDto(result);
    }



}
