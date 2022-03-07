package com.example.bgcpromogearreworked.api.products.product;

import com.example.bgcpromogearreworked.api.products.persistence.Product;
import com.example.bgcpromogearreworked.api.products.persistence.ProductRepository;
import com.example.bgcpromogearreworked.api.products.product.dto.secured.ProductCreate;
import com.example.bgcpromogearreworked.api.products.product.dto.secured.SecuredProductMapper;
import com.example.bgcpromogearreworked.api.products.product.dto.secured.ProductUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Service
@Validated
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepo;
    private final SecuredProductMapper mapper;

    public boolean checkProductExists(long productId) {
        return productRepo.existsById(productId);
    }

    Product handleProductGet(Long productId) {
        return productRepo.findById(productId).orElseThrow();
    }

    Iterable<Product> handleProductBatchGet() {
        return productRepo.findAll();
    }

    Product handleProductCreate(@Valid ProductCreate productCreate) {
        Product product = mapper.fromCreate(productCreate);
        return productRepo.saveAndFlush(product);
    }

    Product handleProductUpdate(@Valid ProductUpdate productUpdate) {
        Product product = productRepo.findById(productUpdate.getId()).orElseThrow();
        product = mapper.fromUpdate(productUpdate, product);
        return productRepo.saveAndFlush(product);
    }

}
