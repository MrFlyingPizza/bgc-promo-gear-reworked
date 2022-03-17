package com.example.bgcpromogearreworked.api.products.product;

import com.example.bgcpromogearreworked.api.products.exceptions.ProductNotFoundException;
import com.example.bgcpromogearreworked.api.products.product.dto.general.GeneralProductBatchResponse;
import com.example.bgcpromogearreworked.api.products.product.dto.general.GeneralProductMapper;
import com.example.bgcpromogearreworked.api.products.product.dto.general.GeneralProductResponse;
import com.example.bgcpromogearreworked.persistence.entities.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/products", produces = MediaType.APPLICATION_JSON_VALUE)
public class GeneralProductController {

    private final ProductService service;
    private final GeneralProductMapper mapper;

    @GetMapping("/{productId}")
    private GeneralProductResponse getProduct(@PathVariable Long productId) {
        if (!service.checkProductExists(productId)) {
            throw new ProductNotFoundException();
        }
        Product result = service.handleProductGet(productId);
        return mapper.toResponse(result);
    }

    @GetMapping
    private GeneralProductBatchResponse getProductBatch() {
        Iterable<Product> result = service.handleProductBatchGet();
        return mapper.toBatchResponse(result);
    }

}
