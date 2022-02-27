package com.example.bgcpromogearreworked.api.product.secured.product.dto;

import com.example.bgcpromogearreworked.api.product.Product;
import org.mapstruct.*;
import org.springframework.data.util.Streamable;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product productCreateDtoToProduct(ProductCreateDto productCreateDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Product productUpdateDtoToProduct(ProductUpdateDto productUpdateDto, @MappingTarget Product targetProduct);

    default ProductResponseDto productToProductResponseDto(Product product) {
        return new ProductResponseDto(product);
    }
    default ProductsResponseDto productsToProductsResponseDto(Iterable<Product> products) {
        return new ProductsResponseDto(Streamable.of(products).toList());
    }
}
