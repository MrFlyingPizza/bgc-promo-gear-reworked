package ca.bgcengineering.promogearreworked.api.orders.general.dto.validator;

import ca.bgcengineering.promogearreworked.api.orders.constraints.SufficientCredit;
import ca.bgcengineering.promogearreworked.api.orders.general.dto.GeneralOrderCreate;
import ca.bgcengineering.promogearreworked.persistence.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.math.BigDecimal;

public class GeneralOrderSufficientCreditValidator implements ConstraintValidator<SufficientCredit, GeneralOrderCreate> {

    @Autowired
    private UserRepository userRepo;

    @Override
    public boolean isValid(GeneralOrderCreate orderCreate, ConstraintValidatorContext constraintValidatorContext) {
        if (orderCreate.getItems() == null || orderCreate.getItems().size() < 1) return true;
        BigDecimal totalCost = BigDecimal.ZERO;
        for (GeneralOrderCreate.NestedOrderItem item : orderCreate.getItems()) {
            totalCost = totalCost.add(item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())));
        }
        return totalCost.compareTo(userRepo.getById(orderCreate.getSubmitterId()).getCredit()) < 0;
    }
}
