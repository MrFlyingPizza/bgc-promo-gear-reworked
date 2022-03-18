package com.example.bgcpromogearreworked.api.products.variant.secured.dto.validation.expectedoptioncount;

import com.example.bgcpromogearreworked.api.products.variant.secured.dto.SecuredProductVariantUpdate;
import com.example.bgcpromogearreworked.persistence.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class VariantUpdateExpectedOptionCountValidator extends ExpectedOptionCountValidator
        implements ConstraintValidator<ExpectedOptionCount, SecuredProductVariantUpdate> {

    @Autowired
    private ProductRepository productRepo;

    @Override
    public boolean isValid(SecuredProductVariantUpdate productVariantUpdate, ConstraintValidatorContext constraintValidatorContext) {
        return validate(productVariantUpdate.getOptionValueIds(), productVariantUpdate.getProductId(), productRepo);
    }
}
