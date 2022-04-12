package ca.bgcengineering.promogearreworked.api.products.variant.secured.dto.validation.uniqueoptionset;

import ca.bgcengineering.promogearreworked.api.products.variant.secured.dto.SecuredProductVariantPartialUpdate;
import ca.bgcengineering.promogearreworked.persistence.repositories.ProductRepository;
import ca.bgcengineering.promogearreworked.persistence.repositories.OptionValueRepository;
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
