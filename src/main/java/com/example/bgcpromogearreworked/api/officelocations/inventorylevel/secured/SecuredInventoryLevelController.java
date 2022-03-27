package com.example.bgcpromogearreworked.api.officelocations.inventorylevel.secured;

import com.example.bgcpromogearreworked.api.officelocations.exceptions.InventoryLevelNotFoundException;
import com.example.bgcpromogearreworked.api.officelocations.exceptions.OfficeLocationNotFoundException;
import com.example.bgcpromogearreworked.api.officelocations.inventorylevel.InventoryLevelService;
import com.example.bgcpromogearreworked.api.officelocations.inventorylevel.secured.dto.*;
import com.example.bgcpromogearreworked.api.officelocations.officelocation.OfficeLocationService;
import com.example.bgcpromogearreworked.api.shared.utils.Utils;
import com.example.bgcpromogearreworked.persistence.entities.InventoryLevel;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/secured/office_locations/{locationId}/inventory_levels",
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class SecuredInventoryLevelController {

    private final InventoryLevelService service;
    private final OfficeLocationService locationService;
    private final SecuredInventoryLevelHandlerService handlerService;
    private final SecuredInventoryLevelMapper mapper;

    @GetMapping("/{variantId}")
    private SecuredInventoryLevelResponse getInventoryLevel(@PathVariable Long locationId, @PathVariable Long variantId) {
        if (!locationService.checkOfficeLocationExists(locationId)) {
            throw new OfficeLocationNotFoundException();
        }
        if (!service.checkInventoryLevelExists(locationId, variantId)) {
            throw new InventoryLevelNotFoundException();
        }
        return mapper.toResponse(handlerService.handleInventoryLevelGet(locationId, variantId));
    }

    @GetMapping
    private SecuredInventoryLevelBatchResponse getInventoryLevelBatch(@PathVariable Long locationId, Pageable pageable) {
        if (!locationService.checkOfficeLocationExists(locationId)) {
            throw new OfficeLocationNotFoundException();
        }
        return mapper.toBatchResponse(handlerService.handleInventoryLevelBatchGet(locationId, pageable));
    }

    @PutMapping("/{variantId}")
    private SecuredInventoryLevelResponse updateInventoryLevel(@PathVariable Long locationId,
                                                               @PathVariable Long variantId,
                                                               @RequestBody SecuredInventoryLevelUpdate inventoryLevelUpdate,
                                                               @AuthenticationPrincipal OidcUser oidcUser) {
        if (!locationService.checkOfficeLocationExists(locationId)) {
            throw new OfficeLocationNotFoundException();
        }
        if (!service.checkInventoryLevelExists(locationId, variantId)) {
            throw new InventoryLevelNotFoundException();
        }
        inventoryLevelUpdate.setLocationId(locationId);
        inventoryLevelUpdate.setVariantId(variantId);
        return mapper.toResponse(handlerService.handleInventoryLevelUpdate(inventoryLevelUpdate, Utils.oidFromOidcUser(oidcUser)));
    }

    @PatchMapping("/{variantId}")
    private SecuredInventoryLevelResponse updateInventoryLevelPartial(@PathVariable Long locationId,
                                                                      @PathVariable Long variantId,
                                                                      @RequestBody SecuredInventoryLevelPartialUpdate inventoryLevelPartialUpdate,
                                                                      @AuthenticationPrincipal OidcUser oidcUser) {
        if (!locationService.checkOfficeLocationExists(locationId)) {
            throw new OfficeLocationNotFoundException();
        }
        if (!service.checkInventoryLevelExists(locationId, variantId)) {
            throw new InventoryLevelNotFoundException();
        }
        inventoryLevelPartialUpdate.setLocationId(locationId);
        inventoryLevelPartialUpdate.setVariantId(variantId);
        return mapper.toResponse(handlerService.handleInventoryLevelPartialUpdate(inventoryLevelPartialUpdate, Utils.oidFromOidcUser(oidcUser)));
    }

}
