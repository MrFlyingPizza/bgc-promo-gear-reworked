package com.example.bgcpromogearreworked.api.inventorylevels.inventorylevel.secured;

import com.example.bgcpromogearreworked.api.inventorylevels.inventorylevel.InventoryLevelService;
import com.example.bgcpromogearreworked.api.inventorylevels.inventorylevel.secured.dto.SecuredInventoryLevelMapper;
import com.example.bgcpromogearreworked.api.inventorylevels.inventorylevel.secured.dto.SecuredInventoryLevelPartialUpdate;
import com.example.bgcpromogearreworked.api.inventorylevels.inventorylevel.secured.dto.SecuredInventoryLevelUpdate;
import com.example.bgcpromogearreworked.persistence.entities.InventoryLevel;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

@Service
@Validated
@RequiredArgsConstructor
public class SecuredInventoryLevelHandlerService {

    private final InventoryLevelService inventoryLevelService;
    private final SecuredInventoryLevelMapper mapper;

    InventoryLevel handleInventoryLevelGet(Long locationId, Long variantId) {
        return inventoryLevelService.getInventoryLevel(locationId, variantId);
    }

    Page<InventoryLevel> handleInventoryLevelBatchGet(Predicate predicate, Pageable pageable) {
        return inventoryLevelService.getInventoryLevels(predicate, pageable);
    }

    List<InventoryLevel> handleInventoryLevelBatchGet() {
        return inventoryLevelService.getInventoryLevels();
    }

    InventoryLevel handleInventoryLevelUpdate(@Valid SecuredInventoryLevelUpdate inventoryLevelUpdate) {
        return inventoryLevelService.updateInventoryLevel(inventoryLevelUpdate.getLocationId(),
                inventoryLevelUpdate.getVariantId(),
                inventoryLevelUpdate,
                mapper::fromUpdate);
    }

    InventoryLevel handleInventoryLevelPartialUpdate(@Valid SecuredInventoryLevelPartialUpdate inventoryLevelPartialUpdate) {
        return inventoryLevelService.updateInventoryLevel(inventoryLevelPartialUpdate.getLocationId(),
                inventoryLevelPartialUpdate.getVariantId(),
                inventoryLevelPartialUpdate,
                mapper::fromPartialUpdate);
    }

}
