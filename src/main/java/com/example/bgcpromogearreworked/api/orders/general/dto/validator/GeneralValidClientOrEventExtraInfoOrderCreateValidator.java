package com.example.bgcpromogearreworked.api.orders.general.dto.validator;

import com.example.bgcpromogearreworked.api.orders.constraints.ValidClientOrEventExtraInfo;
import com.example.bgcpromogearreworked.api.orders.general.dto.GeneralOrderCreate;
import com.example.bgcpromogearreworked.persistence.entities.Order;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class GeneralValidClientOrEventExtraInfoOrderCreateValidator implements ConstraintValidator<ValidClientOrEventExtraInfo, GeneralOrderCreate> {

    @Override
    public boolean isValid(GeneralOrderCreate orderCreate, ConstraintValidatorContext context) {
        Order.OrderType orderType = orderCreate.getType();
        if (orderType == Order.OrderType.CLIENT || orderType == Order.OrderType.EVENT) {
            return orderCreate.getExtraInfo() != null;
        }
        return true;
    }
}
