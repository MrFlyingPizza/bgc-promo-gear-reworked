package com.example.bgcpromogearreworked.api.products.product;

import com.example.bgcpromogearreworked.api.products.persistence.Product;
import com.example.bgcpromogearreworked.api.products.persistence.ProductRepository;
import com.example.bgcpromogearreworked.api.products.product.dto.secured.ProductCreateDto;
import com.example.bgcpromogearreworked.api.products.product.dto.secured.SecuredProductMapper;
import com.example.bgcpromogearreworked.api.products.product.dto.secured.ProductUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
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

    Product handleProductCreate(ProductCreateDto productCreateDto) {
        Product product = mapper.fromCreateDto(productCreateDto);
        return productRepo.save(product);
    }

    Product handleProductUpdate(Long productId, ProductUpdateDto productUpdateDto) {
        Product product = productRepo.findById(productId).orElseThrow();
        product = mapper.fromUpdateDto(productUpdateDto, product);
        return productRepo.save(product);
    }

}
