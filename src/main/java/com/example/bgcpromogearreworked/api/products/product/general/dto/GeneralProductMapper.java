package com.example.bgcpromogearreworked.api.products.product.general.dto;

import com.example.bgcpromogearreworked.persistence.entities.OptionValue;
import com.example.bgcpromogearreworked.persistence.entities.Product;
import com.example.bgcpromogearreworked.persistence.entities.ProductVariant;
import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.util.Streamable;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class GeneralProductMapper {

    public abstract GeneralProductResponse toResponse(Product product);

    public GeneralProductBatchResponse toBatchResponse(Streamable<Product> products) {
        return new GeneralProductBatchResponse(products.map(this::toResponse).toList());
    }

    // only include valid product variants
    @BeforeMapping
    protected void removeInvalidProductVariants(Product product) {
        product.setVariants(product.getVariants().stream().filter(ProductVariant::getIsValid).collect(Collectors.toSet()));
    }

    @Mapping(source = "optionValues", target = "options")
    protected abstract GeneralProductResponse.NestedProductVariant map(ProductVariant productVariant);

    @Mapping(source = "option.id", target = "optionId")
    @Mapping(source = "option.name", target = "name")
    protected abstract GeneralProductResponse.NestedProductVariant.NestedOptionValue map(OptionValue optionValue);

}
