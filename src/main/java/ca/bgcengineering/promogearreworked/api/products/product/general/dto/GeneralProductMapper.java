package ca.bgcengineering.promogearreworked.api.products.product.general.dto;

import ca.bgcengineering.promogearreworked.api.products.variant.ProductVariantAvailability;
import ca.bgcengineering.promogearreworked.api.products.variant.ProductVariantService;
import ca.bgcengineering.promogearreworked.persistence.entities.OptionValue;
import ca.bgcengineering.promogearreworked.persistence.entities.Product;
import ca.bgcengineering.promogearreworked.persistence.entities.ProductVariant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class GeneralProductMapper {

    @Autowired
    private ProductVariantService variantService;

    public abstract GeneralProductResponse toResponse(Product product);

    public GeneralProductBatchResponse toBatchResponse(Page<Product> page) {
        return new GeneralProductBatchResponse(page.getContent().stream().map(this::toResponse).collect(Collectors.toList()),
                page.getTotalPages(),
                page.getNumber(),
                page.isFirst(),
                page.isLast(),
                page.getNumberOfElements(),
                page.getTotalElements(),
                page.getSort().isSorted());
    }

    @Mapping(source = "optionValues", target = "options")
    @Mapping(source = "id", target = "availability")
    protected abstract GeneralProductResponse.NestedProductVariant map(ProductVariant productVariant);

    @Mapping(source = "id", target = "valueId")
    @Mapping(source = "option.id", target = "optionId")
    @Mapping(source = "option.name", target = "name")
    protected abstract GeneralProductResponse.NestedProductVariant.NestedOptionValue map(OptionValue optionValue);

    protected ProductVariantAvailability map(Long variantId) {
        if (variantId == null) return null;
        return variantService.getProductVariantAvailability(variantId);
    }

}
