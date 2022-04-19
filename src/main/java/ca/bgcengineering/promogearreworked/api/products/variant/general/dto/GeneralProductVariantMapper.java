package ca.bgcengineering.promogearreworked.api.products.variant.general.dto;

import ca.bgcengineering.promogearreworked.api.products.variant.ProductVariantAvailability;
import ca.bgcengineering.promogearreworked.api.products.variant.ProductVariantService;
import ca.bgcengineering.promogearreworked.persistence.entities.OptionValue;
import ca.bgcengineering.promogearreworked.persistence.entities.ProductVariant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class GeneralProductVariantMapper {

    @Autowired
    private ProductVariantService service;

    @Mapping(source = "optionValues", target = "options")
    @Mapping(source = "id", target = "availability")
    public abstract GeneralProductVariantResponse toResponse(ProductVariant productVariant);

    public GeneralProductVariantBatchResponse toBatchResponse(List<ProductVariant> productVariants) {
        return new GeneralProductVariantBatchResponse(productVariants.stream().map(this::toResponse).collect(Collectors.toList()));
    }

    @Mapping(source = "id", target = "valueId")
    @Mapping(source = "option.id", target = "optionId")
    @Mapping(source = "option.name", target = "name")
    protected abstract GeneralProductVariantResponse.NestedOptionValue map(OptionValue optionValue);

    protected ProductVariantAvailability map(Long variantId) {
        if (variantId == null) return null;
        return service.getProductVariantAvailability(variantId);
    }

}
