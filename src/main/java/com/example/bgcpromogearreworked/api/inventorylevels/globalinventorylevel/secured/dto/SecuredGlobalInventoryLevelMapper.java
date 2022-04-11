package com.example.bgcpromogearreworked.api.inventorylevels.globalinventorylevel.secured.dto;

import com.example.bgcpromogearreworked.persistence.entities.GlobalInventoryLevel;
import com.example.bgcpromogearreworked.persistence.entities.OptionValue;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class SecuredGlobalInventoryLevelMapper {

    @Transactional
    @Mapping(source = "variant.product", target = "product")
    @Mapping(source = "variant.optionValues", target = "variant.options")
    public abstract SecuredGlobalInventoryLevelResponse toResponse(GlobalInventoryLevel inventoryLevel);

    @Transactional
    public SecuredGlobalInventoryLevelBatchResponse toBatchResponse(List<GlobalInventoryLevel> globalInventoryLevels) {
        return new SecuredGlobalInventoryLevelBatchResponse(globalInventoryLevels.stream().map(this::toResponse).collect(Collectors.toList()));
    }

    @Mapping(source = "id", target = "valueId")
    @Mapping(source = "option.id", target = "optionId")
    @Mapping(source = "option.name", target = "name")
    protected abstract SecuredGlobalInventoryLevelResponse.NestedProductVariant.NestedOptionValue map(OptionValue optionValue);

}
