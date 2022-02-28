package com.example.bgcpromogearreworked.api.products.product.dto.secured;

import com.example.bgcpromogearreworked.api.products.persistence.Product;
import org.mapstruct.*;
import org.springframework.data.util.Streamable;

@Mapper(componentModel = "spring")
public interface SecuredProductMapper {

    Product fromCreateDto(ProductCreateDto productCreateDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Product fromUpdateDto(ProductUpdateDto productUpdateDto, @MappingTarget Product targetProduct);

    default ProductResponseDto toResponseDto(Product product) {
        return new ProductResponseDto(product);
    }
    default ProductBatchResponseDto toBatchResponseDto(Iterable<Product> products) {
        return new ProductBatchResponseDto(Streamable.of(products).toList());
    }
}
