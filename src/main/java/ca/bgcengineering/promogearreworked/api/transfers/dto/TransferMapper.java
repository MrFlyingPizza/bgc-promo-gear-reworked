package ca.bgcengineering.promogearreworked.api.transfers.dto;

import ca.bgcengineering.promogearreworked.api.inventorylevels.exceptions.InventoryLevelNotFoundException;
import ca.bgcengineering.promogearreworked.api.officelocations.exceptions.OfficeLocationNotFoundException;
import ca.bgcengineering.promogearreworked.api.products.exceptions.ProductVariantNotFoundException;
import ca.bgcengineering.promogearreworked.persistence.entities.InventoryLevelId;
import ca.bgcengineering.promogearreworked.persistence.entities.OfficeLocation;
import ca.bgcengineering.promogearreworked.persistence.entities.ProductVariant;
import ca.bgcengineering.promogearreworked.persistence.entities.Transfer;
import ca.bgcengineering.promogearreworked.persistence.repositories.InventoryLevelRepository;
import ca.bgcengineering.promogearreworked.persistence.repositories.OfficeLocationRepository;
import ca.bgcengineering.promogearreworked.persistence.repositories.ProductVariantRepository;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class TransferMapper {

    @Autowired
    private OfficeLocationRepository locationRepo;

    @Autowired
    private ProductVariantRepository variantRepo;

    @Autowired
    private InventoryLevelRepository inventoryRepo;

    @Mapping(source = "originId", target = "origin")
    @Mapping(source = "destinationId", target = "destination")
    @Mapping(source = "variantId", target = "variant")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "originInventoryLevel", ignore = true)
    @Mapping(target = "destinationInventoryLevel", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    public abstract Transfer fromCreate(TransferCreate transferCreate);

    protected OfficeLocation mapOfficeLocationFromId(long id) {
        return locationRepo.findById(id).orElseThrow(OfficeLocationNotFoundException::new);
    }

    protected ProductVariant mapVariantFromId(long id) {
        return variantRepo.findById(id).orElseThrow(ProductVariantNotFoundException::new);
    }

    @AfterMapping
    protected void mapInventoryLevel(TransferCreate source, @MappingTarget Transfer transfer) {
        var originInventoryLevel = inventoryRepo.findById(new InventoryLevelId(source.originId(), source.variantId())).orElseThrow(InventoryLevelNotFoundException::new);
        var destinationInventoryLevel = inventoryRepo.findById(new InventoryLevelId(source.destinationId(), source.variantId())).orElseThrow(InventoryLevelNotFoundException::new);
        transfer.setOriginInventoryLevel(originInventoryLevel);
        transfer.setDestinationInventoryLevel(destinationInventoryLevel);
    }

}
