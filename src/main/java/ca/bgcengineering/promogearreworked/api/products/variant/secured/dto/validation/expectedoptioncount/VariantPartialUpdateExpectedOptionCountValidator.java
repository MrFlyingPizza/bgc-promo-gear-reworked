package ca.bgcengineering.promogearreworked.api.products.variant.secured.dto.validation.expectedoptioncount;

import ca.bgcengineering.promogearreworked.api.products.variant.secured.dto.SecuredProductVariantPartialUpdate;
import ca.bgcengineering.promogearreworked.persistence.entities.ProductVariant;
import ca.bgcengineering.promogearreworked.persistence.repositories.ProductRepository;
import ca.bgcengineering.promogearreworked.persistence.repositories.ProductVariantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Collection;

public class VariantPartialUpdateExpectedOptionCountValidator extends ExpectedOptionCountValidator
        implements ConstraintValidator<ExpectedOptionCount, SecuredProductVariantPartialUpdate> {

    @Autowired
    private ProductRepository productRepo;

    @Autowired
    private ProductVariantRepository variantRepo;

    @Override
    @Transactional(readOnly = true)
    public boolean isValid(SecuredProductVariantPartialUpdate variantPartialUpdate, ConstraintValidatorContext constraintValidatorContext) {
        ProductVariant variant = variantRepo.getById(variantPartialUpdate.getId());
        final boolean isInUse = variantPartialUpdate.getIsInUse() == null ? variant.getIsInUse() : variantPartialUpdate.getIsInUse();
        final Collection<?> values;
        if (variantPartialUpdate.getOptionValueIds() == null) {
            values = variant.getOptionValues();
        } else {
            values = variantPartialUpdate.getOptionValueIds();
        }
        if (variantPartialUpdate.getOptionValueIds() == null && !isInUse) {
            return true;
        }
        return validate(values.size(), productRepo.getById(variantPartialUpdate.getProductId()).getOptions().size());
    }
}
