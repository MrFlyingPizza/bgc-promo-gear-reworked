package com.example.bgcpromogearreworked.api.products.variant.dto.secured.validation.expectedoptioncount;

import com.example.bgcpromogearreworked.api.products.variant.dto.secured.SecuredProductVariantCreate;
import com.example.bgcpromogearreworked.persistence.entities.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class VariantCreateExpectedOptionCountValidator extends ExpectedOptionCountValidator
        implements ConstraintValidator<ExpectedOptionCount, SecuredProductVariantCreate> {

    @Autowired
    private ProductRepository productRepo;

    @Override
    public boolean isValid(SecuredProductVariantCreate productVariantCreate, ConstraintValidatorContext constraintValidatorContext) {
        return validate(productVariantCreate.getOptionValueIds(), productVariantCreate.getProductId(), productRepo);
    }
}
