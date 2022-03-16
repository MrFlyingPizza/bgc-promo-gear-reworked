package com.example.bgcpromogearreworked.api.products.product.dto.general;

import com.example.bgcpromogearreworked.persistence.entities.OptionValue;
import com.example.bgcpromogearreworked.persistence.entities.Product;
import com.example.bgcpromogearreworked.persistence.entities.ProductVariant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.util.Streamable;

@Mapper(componentModel = "spring")
public abstract class GeneralProductMapper {

    public abstract GeneralProductResponse toResponse(Product product);

    public GeneralProductBatchResponse toBatchResponse(Iterable<Product> products) {
        return new GeneralProductBatchResponse(Streamable.of(products).map(this::toResponse).toList());
    }

    @Mapping(source = "optionValues", target = "options")
    protected abstract GeneralProductResponse.NestedProductVariant map(ProductVariant productVariant);

    @Mapping(source = "option.id", target = "optionId")
    @Mapping(source = "option.name", target = "name")
    protected abstract GeneralProductResponse.NestedProductVariant.NestedOptionValue map(OptionValue optionValue);

}
