package com.example.bgcpromogearreworked.api.products.variant.secured.dto.validation.uniqueoptionset;

import com.example.bgcpromogearreworked.api.products.variant.secured.dto.SecuredProductVariantCreate;
import com.example.bgcpromogearreworked.persistence.repositories.ProductRepository;
import com.example.bgcpromogearreworked.persistence.repositories.OptionValueRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class VariantCreateUniqueOptionSetValidator extends UniqueOptionSetValidator
        implements ConstraintValidator<UniqueOptionSet, SecuredProductVariantCreate> {

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
