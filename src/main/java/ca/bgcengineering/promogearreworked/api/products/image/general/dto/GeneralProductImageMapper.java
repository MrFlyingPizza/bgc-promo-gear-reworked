package ca.bgcengineering.promogearreworked.api.products.image.general.dto;

import ca.bgcengineering.promogearreworked.persistence.entities.ProductImage;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class GeneralProductImageMapper {

    public abstract GeneralProductImageResponse toResponse(ProductImage image);

    public GeneralProductImageBatchResponse toBatchResponse(List<ProductImage> images) {
        return new GeneralProductImageBatchResponse(images.stream().map(this::toResponse).collect(Collectors.toList()));
    }

}
