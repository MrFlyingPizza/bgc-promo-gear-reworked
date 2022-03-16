package com.example.bgcpromogearreworked.api.products.variant.dto.secured.validation.expectedoptioncount;

import com.example.bgcpromogearreworked.api.products.variant.dto.secured.SecuredProductVariantPartialUpdate;
import com.example.bgcpromogearreworked.persistence.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class VariantPartialUpdateExpectedOptionCountValidator extends ExpectedOptionCountValidator
        implements ConstraintValidator<ExpectedOptionCount, SecuredProductVariantPartialUpdate> {

    @Autowired
    private ProductRepository productRepo;

    @Override
    public boolean isValid(SecuredProductVariantPartialUpdate productVariantPartialUpdate, ConstraintValidatorContext constraintValidatorContext) {
        return validate(productVariantPartialUpdate.getOptionValueIds(),
                productVariantPartialUpdate.getProductId(),
                productRepo);
    }
}
