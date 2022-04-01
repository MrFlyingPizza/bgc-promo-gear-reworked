package com.example.bgcpromogearreworked.api.orders.general.dto.validator;

import com.example.bgcpromogearreworked.api.orders.constraints.OrderItemAvailable;
import com.example.bgcpromogearreworked.api.orders.general.dto.GeneralOrderCreate;
import com.example.bgcpromogearreworked.persistence.entities.GlobalInventoryLevel;
import com.example.bgcpromogearreworked.persistence.repositories.GlobalInventoryLevelRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

public class GeneralOrderQuantityAvailableCreateValidator implements ConstraintValidator<OrderItemAvailable, GeneralOrderCreate.NestedOrderItem> {

    @Autowired
    private GlobalInventoryLevelRepository globalInventoryLevelRepo;

    @Override
    public boolean isValid(GeneralOrderCreate.NestedOrderItem orderItem, ConstraintValidatorContext context) {
        Optional<GlobalInventoryLevel> inventoryLevel = globalInventoryLevelRepo.findById(orderItem.getVariantId());
        return inventoryLevel.isPresent() && inventoryLevel.get().getApparentQuantity() <= orderItem.getQuantity();
    }
}
