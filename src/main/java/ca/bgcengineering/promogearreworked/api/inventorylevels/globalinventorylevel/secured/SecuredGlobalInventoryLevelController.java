package ca.bgcengineering.promogearreworked.api.inventorylevels.globalinventorylevel.secured;

import ca.bgcengineering.promogearreworked.api.inventorylevels.exceptions.GlobalInventoryLevelNotFound;
import ca.bgcengineering.promogearreworked.api.inventorylevels.globalinventorylevel.secured.dto.SecuredGlobalInventoryLevelBatchResponse;
import ca.bgcengineering.promogearreworked.api.inventorylevels.globalinventorylevel.secured.dto.SecuredGlobalInventoryLevelMapper;
import ca.bgcengineering.promogearreworked.api.inventorylevels.globalinventorylevel.secured.dto.SecuredGlobalInventoryLevelResponse;
import ca.bgcengineering.promogearreworked.api.products.variant.ProductVariantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/secured/inventory_levels/global")
public class SecuredGlobalInventoryLevelController {

    private final ProductVariantService variantService;
    private final SecuredGlobalInventoryLevelHandlerService handlerService;
    private final SecuredGlobalInventoryLevelMapper mapper;

    @GetMapping("/{variantId}")
    private SecuredGlobalInventoryLevelResponse getGlobalInventoryLevel(@PathVariable Long variantId) {
        if (!variantService.checkProductVariantExists(variantId)) {
            throw new GlobalInventoryLevelNotFound();
        }
        return mapper.toResponse(handlerService.handleGlobalInventoryLevelGet(variantId));
    }

    @GetMapping
    private SecuredGlobalInventoryLevelBatchResponse getGlobalInventoryLevelBatch() {
        return mapper.toBatchResponse(handlerService.handleGlobalInventoryLevelBatchGet());
    }

}
