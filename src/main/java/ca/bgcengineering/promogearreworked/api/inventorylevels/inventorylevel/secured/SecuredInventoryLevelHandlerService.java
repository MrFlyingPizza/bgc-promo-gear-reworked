package ca.bgcengineering.promogearreworked.api.inventorylevels.inventorylevel.secured;

import ca.bgcengineering.promogearreworked.api.users.exceptions.UserNotFoundException;
import ca.bgcengineering.promogearreworked.api.users.user.UserService;
import ca.bgcengineering.promogearreworked.api.inventorylevels.inventorylevel.InventoryLevelService;
import ca.bgcengineering.promogearreworked.api.inventorylevels.inventorylevel.secured.dto.SecuredInventoryLevelMapper;
import ca.bgcengineering.promogearreworked.api.inventorylevels.inventorylevel.secured.dto.SecuredInventoryLevelPartialUpdate;
import ca.bgcengineering.promogearreworked.api.inventorylevels.inventorylevel.secured.dto.SecuredInventoryLevelUpdate;
import ca.bgcengineering.promogearreworked.persistence.entities.InventoryLevel;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.time.Clock;
import java.util.UUID;

@Service
@Validated
@RequiredArgsConstructor
public class SecuredInventoryLevelHandlerService {

    private final InventoryLevelService inventoryLevelService;
    private final UserService userService;
    private final SecuredInventoryLevelMapper mapper;

    InventoryLevel handleInventoryLevelGet(Long locationId, Long variantId) {
        return inventoryLevelService.getInventoryLevel(locationId, variantId);
    }

    Page<InventoryLevel> handleInventoryLevelBatchGet(Predicate predicate, Pageable pageable) {
        return inventoryLevelService.getInventoryLevels(predicate, pageable);
    }

    InventoryLevel handleInventoryLevelUpdate(@Valid SecuredInventoryLevelUpdate inventoryLevelUpdate, UUID oid) {
        try {
            inventoryLevelUpdate.setLastManuallyModifiedById(userService.getUser(oid).getId());
        } catch (UserNotFoundException ignored) { }
        inventoryLevelUpdate.setLastManuallyModifiedDate(Clock.systemUTC().instant());
        return inventoryLevelService.updateInventoryLevel(inventoryLevelUpdate.getLocationId(),
                inventoryLevelUpdate.getVariantId(),
                inventoryLevelUpdate,
                mapper::fromUpdate);
    }

    InventoryLevel handleInventoryLevelPartialUpdate(@Valid SecuredInventoryLevelPartialUpdate inventoryLevelPartialUpdate, UUID oid) {
        try {
            inventoryLevelPartialUpdate.setLastManuallyModifiedById(userService.getUser(oid).getId());
        } catch (UserNotFoundException ignored) { }
        inventoryLevelPartialUpdate.setLastManuallyModifiedDate(Clock.systemUTC().instant());
        return inventoryLevelService.updateInventoryLevel(inventoryLevelPartialUpdate.getLocationId(),
                inventoryLevelPartialUpdate.getVariantId(),
                inventoryLevelPartialUpdate,
                mapper::fromPartialUpdate);
    }

}
