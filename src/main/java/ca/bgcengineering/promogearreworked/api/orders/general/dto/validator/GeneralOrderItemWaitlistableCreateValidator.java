package ca.bgcengineering.promogearreworked.api.orders.general.dto.validator;

import ca.bgcengineering.promogearreworked.api.orders.constraints.Waitlistable;
import ca.bgcengineering.promogearreworked.api.orders.general.dto.GeneralOrderCreate;
import ca.bgcengineering.promogearreworked.persistence.repositories.GlobalInventoryLevelRepository;
import ca.bgcengineering.promogearreworked.persistence.repositories.ProductVariantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class GeneralOrderItemWaitlistableCreateValidator implements ConstraintValidator<Waitlistable, GeneralOrderCreate.NestedOrderItem> {

    @Autowired
    private ProductVariantRepository variantRepo;

    @Autowired
    private GlobalInventoryLevelRepository globalInventoryRepo;

    @Override
    @Transactional
    public boolean isValid(GeneralOrderCreate.NestedOrderItem item, ConstraintValidatorContext context) {
        return globalInventoryRepo.getById(item.getVariantId()).getApparentQuantity() >= 0
                || variantRepo.getById(item.getVariantId()).getProduct().getIsWaitListEnabled();
    }
}
