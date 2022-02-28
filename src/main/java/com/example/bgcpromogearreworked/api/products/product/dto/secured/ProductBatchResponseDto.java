package com.example.bgcpromogearreworked.api.products.product.dto.secured;

import com.example.bgcpromogearreworked.api.products.persistence.Product;
import lombok.Getter;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ProductBatchResponseDto {

    private final List<ProductResponseDto> products;

    ProductBatchResponseDto(Collection<Product> products) {
        this.products = products.stream().map(ProductResponseDto::new).collect(Collectors.toList());
    }

}
