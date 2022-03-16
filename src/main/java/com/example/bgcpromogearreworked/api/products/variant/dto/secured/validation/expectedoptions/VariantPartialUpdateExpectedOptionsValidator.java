package com.example.bgcpromogearreworked.api.products.variant.dto.secured.validation.expectedoptions;

import com.example.bgcpromogearreworked.api.products.variant.dto.secured.SecuredProductVariantPartialUpdate;
import com.example.bgcpromogearreworked.persistence.repositories.ProductRepository;
import com.example.bgcpromogearreworked.persistence.repositories.OptionValueRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class VariantPartialUpdateExpectedOptionsValidator extends ExpectedOptionsValidator
        implements ConstraintValidator<ExpectedOptions, SecuredProductVariantPartialUpdate> {

    @Autowired
    private ProductRepository productRepo;

    @Autowired
    private OptionValueRepository optionValueRepo;

    @Override
    public boolean isValid(SecuredProductVariantPartialUpdate productVariantPartialUpdate, ConstraintValidatorContext constraintValidatorContext) {
        return validate(productVariantPartialUpdate.getProductId(),
                productVariantPartialUpdate.getOptionValueIds(),
                productRepo,
                optionValueRepo);
    }
}
