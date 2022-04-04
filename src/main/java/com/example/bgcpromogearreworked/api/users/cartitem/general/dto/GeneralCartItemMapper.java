package com.example.bgcpromogearreworked.api.users.cartitem.general.dto;

import com.example.bgcpromogearreworked.persistence.entities.CartItem;
import com.example.bgcpromogearreworked.persistence.entities.OptionValue;
import com.example.bgcpromogearreworked.persistence.entities.ProductVariant;
import com.example.bgcpromogearreworked.persistence.repositories.ProductVariantRepository;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class GeneralCartItemMapper {

    @Autowired
    private ProductVariantRepository variantRepo;

    @Mapping(source  = "variantId", target = "variant")
    @Mapping(target = "user", ignore = true)
    public abstract CartItem fromCreate(GeneralCartItemCreate cartItemCreate);

    @Transactional
    @Mapping(source  = "variantId", target = "variant")
    @Mapping(target = "user", ignore = true)
    public abstract CartItem fromUpdate(GeneralCartItemUpdate cartItemCreate, @MappingTarget CartItem cartItem);

    // no partial update because there is only one field but could be necessary in the future

    @Transactional
    @Mapping(source = "variant.product.id", target = "productId")
    @Mapping(source = "variant.product.name", target = "productName")
    @Mapping(source = "variant.optionValues", target = "options")
    @Mapping(source = "variant.image", target = "image")
    @Mapping(source = "variant.id", target = "variantId")
    public abstract GeneralCartItemResponse toResponse(CartItem cartItem);

    @Transactional
    public GeneralCartItemBatchResponse toBatchResponse(List<CartItem> cartItems) {
        return new GeneralCartItemBatchResponse(cartItems.stream().map(this::toResponse).collect(Collectors.toList()));
    }

    @Mapping(source = "option.id", target = "optionId")
    @Mapping(source = "option.name", target = "name")
    protected abstract GeneralCartItemResponse.NestedOptionValue map(OptionValue optionValue);

    protected ProductVariant map(Long variantId) {
        return variantRepo.getById(variantId);
    }
}
