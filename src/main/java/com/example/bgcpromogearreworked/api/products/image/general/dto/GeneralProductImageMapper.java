package com.example.bgcpromogearreworked.api.products.image.general.dto;

import com.example.bgcpromogearreworked.persistence.entities.ProductImage;
import org.mapstruct.Mapper;
import org.springframework.data.util.Streamable;

@Mapper(componentModel = "spring")
public abstract class GeneralProductImageMapper {

    public abstract GeneralProductImageResponse toResponse(ProductImage image);

    public GeneralProductImageBatchResponse toBatchResponse(Iterable<ProductImage> images) {
        return new GeneralProductImageBatchResponse(Streamable.of(images).map(this::toResponse).toList());
    }

}
