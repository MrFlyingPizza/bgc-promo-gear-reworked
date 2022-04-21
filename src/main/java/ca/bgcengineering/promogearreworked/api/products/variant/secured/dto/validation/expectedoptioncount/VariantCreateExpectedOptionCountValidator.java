package ca.bgcengineering.promogearreworked.api.products.variant.secured.dto.validation.expectedoptioncount;

import ca.bgcengineering.promogearreworked.api.products.variant.secured.dto.SecuredProductVariantCreate;
import ca.bgcengineering.promogearreworked.persistence.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class VariantCreateExpectedOptionCountValidator extends ExpectedOptionCountValidator
        implements ConstraintValidator<ExpectedOptionCount, SecuredProductVariantCreate> {

    @Autowired
    private ProductRepository productRepo;

    @Override
    @Transactional(readOnly = true)
    public boolean isValid(SecuredProductVariantCreate variantCreate, ConstraintValidatorContext constraintValidatorContext) {
        return validate(variantCreate.getOptionValueIds().size(), productRepo.getById(variantCreate.getProductId()).getOptions().size());
    }
}
