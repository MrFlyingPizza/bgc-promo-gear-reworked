package com.example.bgcpromogearreworked.api.products.product.secured;

import com.example.bgcpromogearreworked.api.products.product.ProductService;
import com.example.bgcpromogearreworked.api.products.product.secured.dto.SecuredProductCreate;
import com.example.bgcpromogearreworked.api.products.product.secured.dto.SecuredProductMapper;
import com.example.bgcpromogearreworked.api.products.product.secured.dto.SecuredProductPartialUpdate;
import com.example.bgcpromogearreworked.api.products.product.secured.dto.SecuredProductUpdate;
import com.example.bgcpromogearreworked.persistence.entities.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Service
@Validated
@RequiredArgsConstructor
public class SecuredProductHandlerService {

    private final SecuredProductMapper mapper;
    private final ProductService productService;

    Product handleProductGet(Long productId) {
        return productService.getProduct(productId);
    }

    Streamable<Product> handleProductBatchGet() {
        return productService.getProducts();
    }

    Product handleProductCreate(@Valid SecuredProductCreate productCreate) {
        return productService.createProduct(productCreate, mapper::fromCreate);
    }

    Product handleProductPartialUpdate(@Valid SecuredProductPartialUpdate productPartialUpdate) {
        return productService.updateProduct(productPartialUpdate.getId(), productPartialUpdate, mapper::fromPartialUpdate);
    }

    Product handleProductUpdate(@Valid SecuredProductUpdate productUpdate) {
        return productService.updateProduct(productUpdate.getId(), productUpdate, mapper::fromUpdate);
    }

}
