package ca.bgcengineering.promogearreworked.api.products.variant.secured.dto.validation.expectedoptions;

import ca.bgcengineering.promogearreworked.api.products.variant.secured.dto.SecuredProductVariantPartialUpdate;
import ca.bgcengineering.promogearreworked.persistence.entities.OptionValue;
import ca.bgcengineering.promogearreworked.persistence.entities.ProductVariant;
import ca.bgcengineering.promogearreworked.persistence.repositories.ProductRepository;
import ca.bgcengineering.promogearreworked.persistence.repositories.OptionValueRepository;
import ca.bgcengineering.promogearreworked.persistence.repositories.ProductVariantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class VariantPartialUpdateExpectedOptionsValidator extends ExpectedOptionsValidator
        implements ConstraintValidator<ExpectedOptions, SecuredProductVariantPartialUpdate> {

    @Autowired
    private ProductVariantRepository variantRepo;

    @Autowired
    private ProductRepository productRepo;

    @Autowired
    private OptionValueRepository optionValueRepo;

    @Override
    @Transactional(readOnly = true)
    public boolean isValid(SecuredProductVariantPartialUpdate variantPartialUpdate, ConstraintValidatorContext constraintValidatorContext) {
        ProductVariant variant = variantRepo.getById(variantPartialUpdate.getId());
        final boolean isInUse = variantPartialUpdate.getIsInUse() == null ? variant.getIsInUse() : variantPartialUpdate.getIsInUse();
        final Set<OptionValue> values;
        if (variantPartialUpdate.getOptionValueIds() == null) {
            values = variant.getOptionValues();
        } else {
            values = variantPartialUpdate.getOptionValueIds().stream().map(id -> optionValueRepo.getById(id)).collect(Collectors.toSet());
        }
        if (variantPartialUpdate.getOptionValueIds() == null && !isInUse) {
            return true;
        }
        return validate(values, productRepo.getById(variantPartialUpdate.getProductId()).getOptions());
    }
}
