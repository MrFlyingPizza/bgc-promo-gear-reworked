package ca.bgcengineering.promogearreworked.api.orders.general.dto.validator;

import ca.bgcengineering.promogearreworked.api.orders.constraints.BigItemQuantityLimit;
import ca.bgcengineering.promogearreworked.api.orders.general.dto.GeneralOrderCreate;
import ca.bgcengineering.promogearreworked.configuration.StoreDefaultsConfig;
import ca.bgcengineering.promogearreworked.persistence.repositories.ProductVariantRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class GeneralOrderBigItemQuantityLimitCreateValidator implements ConstraintValidator<BigItemQuantityLimit, GeneralOrderCreate> {

    @Autowired
    private ProductVariantRepository variantRepo;

    @Autowired
    private StoreDefaultsConfig defaults;

    @Override
    public boolean isValid(GeneralOrderCreate orderCreate, ConstraintValidatorContext context) {
        if (orderCreate.getItems() == null || orderCreate.getItems().size() < 1) return true;
        int totalBigItemQuantity = 0;
        for (GeneralOrderCreate.NestedOrderItem item : orderCreate.getItems()) {
            if (variantRepo.getById(item.getVariantId()).getProduct().getIsBigItem()) {
                totalBigItemQuantity += item.getQuantity();
            }
        }
        return totalBigItemQuantity <= defaults.getBigItemOrderQuantityLimit();
    }
}
