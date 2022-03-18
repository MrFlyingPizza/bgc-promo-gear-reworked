package com.example.bgcpromogearreworked.api.products.product.general;

import com.example.bgcpromogearreworked.api.products.product.ProductService;
import com.example.bgcpromogearreworked.api.products.product.general.dto.GeneralProductMapper;
import com.example.bgcpromogearreworked.persistence.entities.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
@RequiredArgsConstructor
public class GeneralProductHandlerService {

    private final ProductService productService;
    private final GeneralProductMapper mapper;

    Product handleProductGet(Long productId) {
        return productService.getProduct(productId);
    }

    Streamable<Product> handleProductBatchGet() {
        return productService.getProducts();
    }

}
