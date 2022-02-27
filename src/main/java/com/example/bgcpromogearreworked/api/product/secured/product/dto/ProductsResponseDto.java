package com.example.bgcpromogearreworked.api.product.secured.product.dto;

import com.example.bgcpromogearreworked.api.product.Product;
import lombok.Getter;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ProductsResponseDto {

    private final List<ProductResponseDto> products;

    ProductsResponseDto(Collection<Product> products) {
        this.products = products.stream().map(ProductResponseDto::new).collect(Collectors.toList());
    }

}
