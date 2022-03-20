package com.example.bgcpromogearreworked.api.inventorylevels.inventorylevel.secured.dto;

import com.example.bgcpromogearreworked.persistence.entities.InventoryLevel;
import com.example.bgcpromogearreworked.persistence.entities.OfficeLocation;
import com.example.bgcpromogearreworked.persistence.entities.OptionValue;
import com.example.bgcpromogearreworked.persistence.repositories.InventoryLevelRepository;
import com.example.bgcpromogearreworked.persistence.repositories.OfficeLocationRepository;
import com.example.bgcpromogearreworked.persistence.repositories.ProductVariantRepository;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class SecuredInventoryLevelMapper {

    @Autowired
    private ProductVariantRepository variantRepo;

    @Autowired
    private OfficeLocationRepository locationRepo;

    public abstract InventoryLevel fromUpdate(SecuredInventoryLevelUpdate inventoryLevelUpdate,
                                              @MappingTarget InventoryLevel inventoryLevel);

    public abstract InventoryLevel fromPartialUpdate(SecuredInventoryLevelPartialUpdate inventoryLevelPartialUpdate,
                                                     @MappingTarget InventoryLevel inventoryLevel);

    @Mapping(source = "variant.product.name", target = "variant.productName")
    @Mapping(source = "variant.product.id", target = "variant.productId")
    public abstract SecuredInventoryLevelResponse toResponse(InventoryLevel inventoryLevel);

    public SecuredInventoryLevelBatchResponse toBatchResponse(List<InventoryLevel> inventoryLevels) {
        return new SecuredInventoryLevelBatchResponse(inventoryLevels.stream().map(this::toResponse).collect(Collectors.toList()));
    }

    @AfterMapping
    protected void mapProductVariantFromRepoOrNull(@MappingTarget InventoryLevel inventoryLevel) {
        if (inventoryLevel.getVariant() == null) {
            return;
        }
        Long variantId = inventoryLevel.getVariant().getId();
        if (variantId == null) {
            inventoryLevel.setVariant(null);
        } else {
            inventoryLevel.setVariant(variantRepo.getById(variantId));
        }
    }

    @AfterMapping
    protected void mapOfficeLocationFromRepoOrNull(@MappingTarget InventoryLevel inventoryLevel) {
        if (inventoryLevel.getLocation() == null) {
            return;
        }
        Long locationId = inventoryLevel.getLocation().getId();
        if (locationId == null) {
            inventoryLevel.setLocation(null);
        } else {
            inventoryLevel.setLocation(locationRepo.getById(locationId));
        }
    }

    @Mapping(source = "option.id", target = "optionId")
    @Mapping(source = "option.name", target = "name")
    protected abstract SecuredInventoryLevelResponse.NestedVariant.NestedOptionValue map(OptionValue optionValue);
}
