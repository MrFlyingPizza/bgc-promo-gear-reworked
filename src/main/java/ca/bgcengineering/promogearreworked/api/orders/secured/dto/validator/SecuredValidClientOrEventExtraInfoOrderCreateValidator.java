package ca.bgcengineering.promogearreworked.api.orders.secured.dto.validator;

import ca.bgcengineering.promogearreworked.api.orders.constraints.ValidClientOrEventExtraInfo;
import ca.bgcengineering.promogearreworked.api.orders.secured.dto.SecuredOrderCreate;
import ca.bgcengineering.promogearreworked.persistence.entities.Order;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class SecuredValidClientOrEventExtraInfoOrderCreateValidator implements ConstraintValidator<ValidClientOrEventExtraInfo, SecuredOrderCreate> {
    @Override
    public boolean isValid(SecuredOrderCreate orderCreate, ConstraintValidatorContext constraintValidatorContext) {
        Order.Type type = orderCreate.getType();
        return (type == Order.Type.CLIENT || type == Order.Type.EVENT) == (orderCreate.getExtraInfo() != null);
    }
}
