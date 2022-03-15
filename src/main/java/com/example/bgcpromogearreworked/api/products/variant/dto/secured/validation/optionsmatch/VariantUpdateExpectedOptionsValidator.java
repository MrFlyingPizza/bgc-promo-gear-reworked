package com.example.bgcpromogearreworked.api.products.variant.dto.secured.validation.optionsmatch;

import com.example.bgcpromogearreworked.api.products.variant.dto.secured.SecuredProductVariantUpdate;
import com.example.bgcpromogearreworked.persistence.entities.ProductRepository;
import com.example.bgcpromogearreworked.persistence.repositories.OptionValueRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class VariantUpdateExpectedOptionsValidator extends ExpectedOptionsValidator
        implements ConstraintValidator<ExpectedOptions, SecuredProductVariantUpdate> {

    @Autowired
    private ProductRepository productRepo;

    @Autowired
    private OptionValueRepository optionValueRepo;

    @Override
    public boolean isValid(SecuredProductVariantUpdate securedProductVariantUpdate, ConstraintValidatorContext constraintValidatorContext) {
        return validate(securedProductVariantUpdate.getProductId(),
                securedProductVariantUpdate.getOptionValueIds(),
                productRepo,
                optionValueRepo);
    }
}
