package ca.bgcengineering.promogearreworked.api.orders.general.dto.validator;

import ca.bgcengineering.promogearreworked.api.orders.constraints.BigItemQuantityLimit;
import ca.bgcengineering.promogearreworked.api.orders.general.dto.GeneralOrderCreate;
import ca.bgcengineering.promogearreworked.persistence.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class GeneralOrderBigItemQuantityLimitCreateValidator implements ConstraintValidator<BigItemQuantityLimit, GeneralOrderCreate> {

    @Autowired
    private UserRepository userRepo;

    @Override
    public boolean isValid(GeneralOrderCreate orderCreate, ConstraintValidatorContext context) {
        if (orderCreate.getItems() == null || orderCreate.getItems().size() < 1) return true;
        // TODO: 2022-05-31 implement
        return false;
    }
}
