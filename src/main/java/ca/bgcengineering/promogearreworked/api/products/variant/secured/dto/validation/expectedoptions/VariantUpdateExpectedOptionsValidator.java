package ca.bgcengineering.promogearreworked.api.products.variant.secured.dto.validation.expectedoptions;

import ca.bgcengineering.promogearreworked.api.products.variant.secured.dto.SecuredProductVariantUpdate;
import ca.bgcengineering.promogearreworked.persistence.repositories.ProductRepository;
import ca.bgcengineering.promogearreworked.persistence.repositories.OptionValueRepository;
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
