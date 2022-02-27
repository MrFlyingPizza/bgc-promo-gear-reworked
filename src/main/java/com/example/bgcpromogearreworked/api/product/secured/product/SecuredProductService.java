package com.example.bgcpromogearreworked.api.product.secured.product;

import com.example.bgcpromogearreworked.api.product.Product;
import com.example.bgcpromogearreworked.api.product.ProductRepository;
import com.example.bgcpromogearreworked.api.product.secured.product.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SecuredProductService {

    private final ProductRepository productRepo;
    private final ProductMapper mapper;

    public boolean doesProductExist(long productId) {
        return productRepo.existsById(productId);
    }

    ProductResponseDto handleProductGet(Long productId) {
        return mapper.productToProductResponseDto(productRepo.findById(productId).orElseThrow());
    }

    ProductsResponseDto handleProductGetMultiple() {
        return mapper.productsToProductsResponseDto(productRepo.findAll());
    }

    ProductResponseDto handleProductCreate(ProductCreateDto productCreateDto) {
        Product product = mapper.productCreateDtoToProduct(productCreateDto);
        product = productRepo.save(product);
        return mapper.productToProductResponseDto(product);
    }

    ProductResponseDto handleProductUpdate(Long productId, ProductUpdateDto productUpdateDto) {
        Product product = productRepo.findById(productId).orElseThrow();
        product = mapper.productUpdateDtoToProduct(productUpdateDto, product);
        product = productRepo.save(product);
        return mapper.productToProductResponseDto(product);
    }

}
