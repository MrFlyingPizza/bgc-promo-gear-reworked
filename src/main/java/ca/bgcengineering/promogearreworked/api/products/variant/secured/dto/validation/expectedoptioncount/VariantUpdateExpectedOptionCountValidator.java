package ca.bgcengineering.promogearreworked.api.products.variant.secured.dto.validation.expectedoptioncount;

import ca.bgcengineering.promogearreworked.api.products.variant.secured.dto.SecuredProductVariantUpdate;
import ca.bgcengineering.promogearreworked.persistence.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class VariantUpdateExpectedOptionCountValidator extends ExpectedOptionCountValidator
        implements ConstraintValidator<ExpectedOptionCount, SecuredProductVariantUpdate> { // TODO: 2022-03-19 check if patch works with this

    @Autowired
    private ProductRepository productRepo;

    @Override
    public boolean isValid(SecuredProductVariantUpdate productVariantUpdate, ConstraintValidatorContext constraintValidatorContext) {
        return validate(productVariantUpdate.getOptionValueIds(), productVariantUpdate.getProductId(), productRepo);
    }
}
