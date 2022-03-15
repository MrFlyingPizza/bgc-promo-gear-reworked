package com.example.bgcpromogearreworked.api.products.variant.dto.secured.validation.expectedoptions;

import com.example.bgcpromogearreworked.api.products.variant.dto.secured.SecuredProductVariantCreate;
import com.example.bgcpromogearreworked.persistence.entities.ProductRepository;
import com.example.bgcpromogearreworked.persistence.repositories.OptionValueRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class VariantCreateExpectedOptionsValidator extends ExpectedOptionsValidator
        implements ConstraintValidator<ExpectedOptions, SecuredProductVariantCreate> {

    @Autowired
    private ProductRepository productRepo;

    @Autowired
    private OptionValueRepository optionValueRepo;

    @Override
    public boolean isValid(SecuredProductVariantCreate productVariantCreate, ConstraintValidatorContext constraintValidatorContext) {
        return validate(productVariantCreate.getProductId(),
                productVariantCreate.getOptionValueIds(),
                productRepo,
                optionValueRepo);
    }
}
