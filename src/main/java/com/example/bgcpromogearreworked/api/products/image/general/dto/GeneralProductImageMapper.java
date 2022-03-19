package com.example.bgcpromogearreworked.api.products.image.general.dto;

import com.example.bgcpromogearreworked.persistence.entities.ProductImage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.util.Streamable;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class GeneralProductImageMapper {

    public abstract GeneralProductImageResponse toResponse(ProductImage image);

    public GeneralProductImageBatchResponse toBatchResponse(List<ProductImage> images) {
        return new GeneralProductImageBatchResponse(images.stream().map(this::toResponse).collect(Collectors.toList()));
    }

}
