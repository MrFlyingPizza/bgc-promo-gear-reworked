package ca.bgcengineering.promogearreworked.api.users.cartitem.general.dto;

import ca.bgcengineering.promogearreworked.api.products.variant.ProductVariantAvailability;
import ca.bgcengineering.promogearreworked.api.products.variant.ProductVariantService;
import ca.bgcengineering.promogearreworked.persistence.entities.CartItem;
import ca.bgcengineering.promogearreworked.persistence.entities.OptionValue;
import ca.bgcengineering.promogearreworked.persistence.entities.ProductVariant;
import ca.bgcengineering.promogearreworked.persistence.repositories.ProductVariantRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class GeneralCartItemMapper {

    @Autowired
    private ProductVariantRepository variantRepo;

    @Autowired
    private ProductVariantService variantService;

    @Mapping(source  = "variantId", target = "variant")
    @Mapping(target = "user", ignore = true)
    public abstract CartItem fromCreate(GeneralCartItemCreate cartItemCreate);

    @Mapping(source  = "variantId", target = "variant")
    @Mapping(target = "user", ignore = true)
    public abstract CartItem fromUpdate(GeneralCartItemUpdate cartItemCreate, @MappingTarget CartItem cartItem);

    // no partial update because there is only one field but could be necessary in the future

    @Mapping(source = "variant.product", target = "product")
    @Mapping(source = "variant.optionValues", target = "variant.options")
    @Mapping(source = "variant.id", target = "variant.availability")
    public abstract GeneralCartItemResponse toResponse(CartItem cartItem);

    public GeneralCartItemBatchResponse toBatchResponse(List<CartItem> cartItems) {
        return new GeneralCartItemBatchResponse(cartItems.stream().map(this::toResponse).collect(Collectors.toList()));
    }

    @Mapping(source = "id", target = "valueId")
    @Mapping(source = "option.id", target = "optionId")
    @Mapping(source = "option.name", target = "name")
    protected abstract GeneralCartItemResponse.NestedOptionValue map(OptionValue optionValue);

    protected ProductVariant map(Long variantId) {
        return variantId != null && variantId != 0 ? variantRepo.getById(variantId) : null;
    }

    protected ProductVariantAvailability mapAvailability(Long variantId) {
        if (variantId == null) return null;
        return variantService.getProductVariantAvailability(variantId);
    }
}
