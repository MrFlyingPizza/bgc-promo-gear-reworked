package com.example.bgcpromogearreworked.api.inventorylevels.globalinventorylevel.secured;

import com.example.bgcpromogearreworked.api.inventorylevels.globalinventorylevel.GlobalInventoryLevelService;
import com.example.bgcpromogearreworked.persistence.entities.GlobalInventoryLevel;
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
