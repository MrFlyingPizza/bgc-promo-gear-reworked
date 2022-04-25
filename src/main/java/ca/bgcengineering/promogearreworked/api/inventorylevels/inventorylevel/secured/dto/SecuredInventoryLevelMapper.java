package ca.bgcengineering.promogearreworked.api.inventorylevels.inventorylevel.secured.dto;

import ca.bgcengineering.promogearreworked.persistence.entities.*;
import ca.bgcengineering.promogearreworked.persistence.repositories.OfficeLocationRepository;
import ca.bgcengineering.promogearreworked.persistence.repositories.ProductVariantRepository;
import ca.bgcengineering.promogearreworked.persistence.repositories.UserRepository;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class SecuredInventoryLevelMapper {

    @Autowired
    private ProductVariantRepository variantRepo;

    @Autowired
    private OfficeLocationRepository locationRepo;

    @Autowired
    private UserRepository userRepo;

    @Mapping(source = "variantId", target = "variant")
    @Mapping(source = "locationId", target = "location")
    @Mapping(source = "lastManuallyModifiedById", target = "lastManuallyModifiedBy")
    @Mapping(target = "neededQuantity", ignore = true)
    @Mapping(target = "reservedQuantity", ignore = true)
    public abstract InventoryLevel fromUpdate(SecuredInventoryLevelUpdate inventoryLevelUpdate,
                                              @MappingTarget InventoryLevel inventoryLevel);

    @Mapping(source = "variantId", target = "variant")
    @Mapping(source = "locationId", target = "location")
    @Mapping(source = "lastManuallyModifiedById", target = "lastManuallyModifiedBy")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "neededQuantity", ignore = true)
    @Mapping(target = "reservedQuantity", ignore = true)
    public abstract InventoryLevel fromPartialUpdate(SecuredInventoryLevelPartialUpdate inventoryLevelPartialUpdate,
                                                     @MappingTarget InventoryLevel inventoryLevel);

    @Mapping(source = "variant.product", target = "product")
    @Mapping(source = "variant.optionValues", target = "variant.options")
    public abstract SecuredInventoryLevelResponse toResponse(InventoryLevel inventoryLevel);

    public SecuredInventoryLevelBatchResponse toBatchResponse(Page<InventoryLevel> page) {
        return new SecuredInventoryLevelBatchResponse(page.getContent().stream().map(this::toResponse).collect(Collectors.toList()),
                page.getTotalPages(),
                page.getNumber(),
                page.isFirst(),
                page.isLast(),
                page.getNumberOfElements(),
                page.getTotalElements(),
                page.getSort().isSorted());
    }

    @Mapping(source = "id", target = "valueId")
    @Mapping(source = "option.id", target = "optionId")
    @Mapping(source = "option.name", target = "name")
    protected abstract SecuredInventoryLevelResponse.NestedProductVariant.NestedOptionValue map(OptionValue optionValue);

    protected ProductVariant mapVariantFromId(Long variantId) {
        return variantId != null && variantId != 0 ? variantRepo.getById(variantId) : null;
    }

    protected OfficeLocation mapLocationFromId(Long locationId) {
        return locationId != null && locationId != 0 ? locationRepo.getById(locationId) : null;
    }

    protected User mapUserFromId(Long userId) {
        return userId != null && userId != 0 ? userRepo.getById(userId) : null;
    }
}
