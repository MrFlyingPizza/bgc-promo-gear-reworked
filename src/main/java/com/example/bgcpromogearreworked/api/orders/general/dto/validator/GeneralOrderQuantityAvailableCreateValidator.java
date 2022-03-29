package com.example.bgcpromogearreworked.api.orders.general.dto.validator;

import com.example.bgcpromogearreworked.api.orders.constraints.OrderItemAvailable;
import com.example.bgcpromogearreworked.api.orders.general.dto.GeneralOrderCreate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class GeneralOrderQuantityAvailableCreateValidator implements ConstraintValidator<OrderItemAvailable, GeneralOrderCreate.NestedOrderItem> {


    // TODO: 2022-03-27 finish implement
    @Override
    public boolean isValid(GeneralOrderCreate.NestedOrderItem orderItem, ConstraintValidatorContext context) {
        return true;
    }
}
