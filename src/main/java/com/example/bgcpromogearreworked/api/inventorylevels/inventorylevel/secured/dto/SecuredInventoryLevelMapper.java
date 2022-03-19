package com.example.bgcpromogearreworked.api.inventorylevels.inventorylevel.secured.dto;

import com.example.bgcpromogearreworked.persistence.entities.InventoryLevel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class SecuredInventoryLevelMapper {

    public abstract InventoryLevel fromUpdate(SecuredInventoryLevelUpdate inventoryLevelUpdate,
                                              @MappingTarget InventoryLevel inventoryLevel);

    public abstract InventoryLevel fromPartialUpdate(SecuredInventoryLevelPartialUpdate inventoryLevelPartialUpdate,
                                                     @MappingTarget InventoryLevel inventoryLevel);

    @Mapping(source = "productVariant", target = "variant")
    @Mapping(source = "officeLocation", target = "location")
    public abstract SecuredInventoryLevelResponse toResponse(InventoryLevel inventoryLevel);

    public SecuredInventoryLevelBatchResponse toBatchResponse(List<InventoryLevel> inventoryLevels) {
        return new SecuredInventoryLevelBatchResponse(inventoryLevels.stream().map(this::toResponse).collect(Collectors.toList()));
    }

}
