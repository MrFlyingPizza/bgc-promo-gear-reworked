package ca.bgcengineering.promogearreworked.api.orders.general.dto.validator;

import ca.bgcengineering.promogearreworked.api.orders.constraints.QuantityAvailable;
import ca.bgcengineering.promogearreworked.persistence.entities.GlobalInventoryLevel;
import ca.bgcengineering.promogearreworked.persistence.repositories.GlobalInventoryLevelRepository;
import ca.bgcengineering.promogearreworked.api.orders.general.dto.GeneralOrderCreate;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

public class GeneralOrderQuantityAvailableCreateValidator implements ConstraintValidator<QuantityAvailable, GeneralOrderCreate.NestedOrderItem> {

    @Autowired
    private GlobalInventoryLevelRepository globalInventoryLevelRepo;

    @Override
    public boolean isValid(GeneralOrderCreate.NestedOrderItem orderItem, ConstraintValidatorContext context) {
        Optional<GlobalInventoryLevel> inventoryLevel = globalInventoryLevelRepo.findById(orderItem.getVariantId());
        return inventoryLevel.isPresent() && inventoryLevel.get().getApparentQuantity() <= orderItem.getQuantity();
    }
}
