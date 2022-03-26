package com.example.bgcpromogearreworked.api.shared.validation.constraints.productvariantinuse;

import com.example.bgcpromogearreworked.persistence.repositories.ProductVariantRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class InUseProductVariantExistsValidator implements ConstraintValidator<InUseProductVariantExists, Long> {

    @Autowired
    private ProductVariantRepository variantRepo;

    @Override
    public boolean isValid(Long variantId, ConstraintValidatorContext context) {
        return variantId == null || variantRepo.existsByIdAndIsInUse(variantId, true);
    }
}
