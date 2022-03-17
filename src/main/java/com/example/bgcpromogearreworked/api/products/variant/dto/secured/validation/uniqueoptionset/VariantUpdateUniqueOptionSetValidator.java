package com.example.bgcpromogearreworked.api.products.variant.dto.secured.validation.uniqueoptionset;

import com.example.bgcpromogearreworked.api.products.variant.dto.secured.SecuredProductVariantUpdate;
import com.example.bgcpromogearreworked.persistence.repositories.ProductRepository;
import com.example.bgcpromogearreworked.persistence.repositories.OptionValueRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class VariantUpdateUniqueOptionSetValidator extends UniqueOptionSetValidator
        implements ConstraintValidator<UniqueOptionSet, SecuredProductVariantUpdate> {

    @Autowired
    private ProductRepository productRepo;

    @Autowired
    private OptionValueRepository optionValueRepo;

    @Override
    public boolean isValid(SecuredProductVariantUpdate productVariantUpdate, ConstraintValidatorContext constraintValidatorContext) {
        return validate(productVariantUpdate.getProductId(),
                productVariantUpdate.getOptionValueIds(),
                productRepo,
                optionValueRepo);
    }
}
