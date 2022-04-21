package ca.bgcengineering.promogearreworked.api.products.variant.secured.dto.validation.expectedoptions;

import ca.bgcengineering.promogearreworked.api.products.variant.secured.dto.SecuredProductVariantUpdate;
import ca.bgcengineering.promogearreworked.persistence.repositories.OptionValueRepository;
import ca.bgcengineering.promogearreworked.persistence.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.stream.Collectors;

public class VariantUpdateExpectedOptionsValidator extends ExpectedOptionsValidator
        implements ConstraintValidator<ExpectedOptions, SecuredProductVariantUpdate> {

    @Autowired
    private ProductRepository productRepo;

    @Autowired
    private OptionValueRepository optionValueRepo;

    @Override
    @Transactional(readOnly = true)
    public boolean isValid(SecuredProductVariantUpdate variantUpdate, ConstraintValidatorContext constraintValidatorContext) {
        return validate(variantUpdate.getOptionValueIds().stream().map(id -> optionValueRepo.getById(id)).collect(Collectors.toSet()),
                productRepo.getById(variantUpdate.getProductId()).getOptions());
    }
}
