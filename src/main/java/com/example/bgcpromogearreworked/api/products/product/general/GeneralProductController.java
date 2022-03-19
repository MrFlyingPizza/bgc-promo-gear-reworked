package com.example.bgcpromogearreworked.api.products.product.general;

import com.example.bgcpromogearreworked.api.products.exceptions.ProductNotFoundException;
import com.example.bgcpromogearreworked.api.products.product.ProductService;
import com.example.bgcpromogearreworked.api.products.product.general.dto.GeneralProductBatchResponse;
import com.example.bgcpromogearreworked.api.products.product.general.dto.GeneralProductMapper;
import com.example.bgcpromogearreworked.api.products.product.general.dto.GeneralProductResponse;
import com.example.bgcpromogearreworked.persistence.entities.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Streamable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/products", produces = MediaType.APPLICATION_JSON_VALUE)
class GeneralProductController {

    private final ProductService productService;
    private final GeneralProductHandlerService handlerService;
    private final GeneralProductMapper mapper;

    @GetMapping("/{productId}")
    private GeneralProductResponse getProduct(@PathVariable Long productId) {
        if (!productService.checkProductExists(productId)) {
            throw new ProductNotFoundException();
        }
        Product result = handlerService.handleProductGet(productId);
        return mapper.toResponse(result);
    }

    @GetMapping
    private GeneralProductBatchResponse getProductBatch() {
        List<Product> result = handlerService.handleProductBatchGet();
        return mapper.toBatchResponse(result);
    }

}
