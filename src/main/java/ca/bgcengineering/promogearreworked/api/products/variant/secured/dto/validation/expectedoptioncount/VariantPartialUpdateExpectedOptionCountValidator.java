package ca.bgcengineering.promogearreworked.api.products.variant.secured.dto.validation.expectedoptioncount;

import ca.bgcengineering.promogearreworked.api.products.variant.secured.dto.SecuredProductVariantPartialUpdate;
import ca.bgcengineering.promogearreworked.persistence.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class VariantPartialUpdateExpectedOptionCountValidator extends ExpectedOptionCountValidator
        implements ConstraintValidator<ExpectedOptionCount, SecuredProductVariantPartialUpdate> {

    @Autowired
    private ProductRepository productRepo;

    @Override
    public boolean isValid(SecuredProductVariantPartialUpdate productVariantPartialUpdate, ConstraintValidatorContext constraintValidatorContext) {
        if (productVariantPartialUpdate.getOptionValueIds() == null && !productVariantPartialUpdate.getIsInUse()) {
            return true;
        }
        return validate(productVariantPartialUpdate.getOptionValueIds(),
                productVariantPartialUpdate.getProductId(),
                productRepo);
    }
}
