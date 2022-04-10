package com.example.bgcpromogearreworked.api.orders.general.dto.validator;

import com.example.bgcpromogearreworked.api.orders.constraints.Waitlistable;
import com.example.bgcpromogearreworked.api.orders.general.dto.GeneralOrderCreate;
import com.example.bgcpromogearreworked.persistence.repositories.ProductVariantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class GeneralOrderItemWaitlistableCreateValidator implements ConstraintValidator<Waitlistable, GeneralOrderCreate.NestedOrderItem> {

    @Autowired
    private ProductVariantRepository variantRepo;

    @Override
    @Transactional
    public boolean isValid(GeneralOrderCreate.NestedOrderItem item, ConstraintValidatorContext context) {
        return variantRepo.getById(item.getVariantId()).getProduct().getIsWaitListEnabled();
    }
}
