package com.example.bgcpromogearreworked.api.products.product.dto.secured;

import com.example.bgcpromogearreworked.api.products.persistence.Product;
import org.mapstruct.*;
import org.springframework.data.util.Streamable;

@Mapper(componentModel = "spring")
public abstract class SecuredProductMapper {

    @Mapping(source = "categoryId", target = "category.id")
    public abstract Product fromCreate(ProductCreate productCreate);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "categoryId", target = "category.id")
    public abstract Product fromUpdate(ProductUpdate productUpdate, @MappingTarget Product targetProduct);

    public ProductResponse toResponse(Product product) {
        return new ProductResponse(product);
    }
    public ProductBatchResponse toBatchResponse(Iterable<Product> products) {
        return new ProductBatchResponse(Streamable.of(products).toList());
    }
}
