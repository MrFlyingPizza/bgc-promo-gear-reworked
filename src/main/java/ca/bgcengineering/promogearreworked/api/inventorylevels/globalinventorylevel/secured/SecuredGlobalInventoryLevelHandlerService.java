package ca.bgcengineering.promogearreworked.api.inventorylevels.globalinventorylevel.secured;

import ca.bgcengineering.promogearreworked.api.inventorylevels.globalinventorylevel.GlobalInventoryLevelService;
import ca.bgcengineering.promogearreworked.persistence.entities.GlobalInventoryLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SecuredGlobalInventoryLevelHandlerService {

    private final GlobalInventoryLevelService service;

    GlobalInventoryLevel handleGlobalInventoryLevelGet(Long variantId) {
        return service.getGlobalInventoryLevel(variantId);
    }

    List<GlobalInventoryLevel> handleGlobalInventoryLevelBatchGet() {
        return service.getGlobalInventoryLevels();
    }

}
