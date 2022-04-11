package ca.bgcengineering.promogearreworked.api.products.variant.general.dto;

import ca.bgcengineering.promogearreworked.persistence.entities.OptionValue;
import ca.bgcengineering.promogearreworked.persistence.entities.ProductVariant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class GeneralProductVariantMapper {

    @Mapping(source = "optionValues", target = "options")
    public abstract GeneralProductVariantResponse toResponse(ProductVariant productVariant);

    public GeneralProductVariantBatchResponse toBatchResponse(List<ProductVariant> productVariants) {
        return new GeneralProductVariantBatchResponse(productVariants.stream().map(this::toResponse).collect(Collectors.toList()));
    }

    @Mapping(source = "option.id", target = "optionId")
    @Mapping(source = "option.name", target = "name")
    protected abstract GeneralProductVariantResponse.NestedOptionValue map(OptionValue optionValue);

}
