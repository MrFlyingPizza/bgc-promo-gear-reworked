package com.example.bgcpromogearreworked.api.products.variant.secured.dto.validation.uniqueoptionset;

import com.example.bgcpromogearreworked.api.products.variant.secured.dto.SecuredProductVariantPartialUpdate;
import com.example.bgcpromogearreworked.persistence.repositories.ProductRepository;
import com.example.bgcpromogearreworked.persistence.repositories.OptionValueRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class VariantPartialUpdateUniqueOptionSetValidator extends UniqueOptionSetValidator
        implements ConstraintValidator<UniqueOptionSet, SecuredProductVariantPartialUpdate> {

    @Autowired
    private ProductRepository productRepo;

    @Autowired
    private OptionValueRepository optionValueRepo;

    @Override
    public boolean isValid(SecuredProductVariantPartialUpdate productVariantPartialUpdate, ConstraintValidatorContext constraintValidatorContext) {
        if (productVariantPartialUpdate.getOptionValueIds() == null && !productVariantPartialUpdate.getIsInUse()) {
            return true;
        }
        return validate(productVariantPartialUpdate.getProductId(),
                productVariantPartialUpdate.getOptionValueIds(),
                productRepo,
                optionValueRepo);
    }
}
