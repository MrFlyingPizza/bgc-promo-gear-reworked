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
        final Collection<?> values = variantPartialUpdate.getIsInUse() != null
                && variantPartialUpdate.getIsInUse()
                && variantPartialUpdate.getOptionValueIds() == null
                ? variantRepo.getById(variantPartialUpdate.getId()).getOptionValues()
                : variantPartialUpdate.getOptionValueIds();

        return values == null || validate(values.size(), productRepo.getById(variantPartialUpdate.getProductId()).getOptions().size());
    }
}
