package ca.bgcengineering.promogearreworked.api.transfers.dto.validation;

import ca.bgcengineering.promogearreworked.api.inventorylevels.inventorylevel.InventoryLevelService;
import ca.bgcengineering.promogearreworked.api.transfers.dto.TransferCreate;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class SufficientTransferQuantityCreateValidator implements ConstraintValidator<SufficientTransferQuantity, TransferCreate> {

    @Autowired
    private InventoryLevelService inventoryService;

    @Override
    public boolean isValid(TransferCreate transferCreate, ConstraintValidatorContext constraintValidatorContext) {
        return inventoryService.getInventoryLevel(transferCreate.originId(), transferCreate.variantId()).getAvailableQuantity() >= transferCreate.quantity();
    }
}
