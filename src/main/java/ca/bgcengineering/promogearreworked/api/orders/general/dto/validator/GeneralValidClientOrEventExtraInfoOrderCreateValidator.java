package ca.bgcengineering.promogearreworked.api.orders.general.dto.validator;

import ca.bgcengineering.promogearreworked.api.orders.constraints.ValidClientOrEventExtraInfo;
import ca.bgcengineering.promogearreworked.persistence.entities.Order;
import ca.bgcengineering.promogearreworked.api.orders.general.dto.GeneralOrderCreate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class GeneralValidClientOrEventExtraInfoOrderCreateValidator implements ConstraintValidator<ValidClientOrEventExtraInfo, GeneralOrderCreate> {

    @Override
    public boolean isValid(GeneralOrderCreate orderCreate, ConstraintValidatorContext context) {
        Order.Type type = orderCreate.getType();
        return (type == Order.Type.CLIENT || type == Order.Type.EVENT) == (orderCreate.getExtraInfo() != null);
    }
}
