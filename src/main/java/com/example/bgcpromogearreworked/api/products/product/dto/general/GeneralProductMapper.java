package com.example.bgcpromogearreworked.api.products.product.dto.general;

import com.example.bgcpromogearreworked.persistence.entities.OptionValue;
import com.example.bgcpromogearreworked.persistence.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class GeneralProductMapper {

    public abstract GeneralProductResponse toResponse(Product product);

    @Mapping(source = "value", target = ".")
    protected abstract String map(OptionValue optionValue);

}
