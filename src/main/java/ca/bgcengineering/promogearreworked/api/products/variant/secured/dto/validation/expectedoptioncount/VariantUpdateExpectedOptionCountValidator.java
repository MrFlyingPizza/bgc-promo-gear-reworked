package ca.bgcengineering.promogearreworked.api.products.variant.secured.dto.validation.expectedoptioncount;

import ca.bgcengineering.promogearreworked.api.products.variant.secured.dto.SecuredProductVariantUpdate;
import ca.bgcengineering.promogearreworked.persistence.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class VariantUpdateExpectedOptionCountValidator extends ExpectedOptionCountValidator
        implements ConstraintValidator<ExpectedOptionCount, SecuredProductVariantUpdate> {

    @Autowired
    private ProductRepository productRepo;

    @Override
    @Transactional(readOnly = true)
    public boolean isValid(SecuredProductVariantUpdate variantUpdate, ConstraintValidatorContext constraintValidatorContext) {
        return validate(variantUpdate.getOptionValueIds().size(), productRepo.getById(variantUpdate.getProductId()).getOptions().size());
    }
}
