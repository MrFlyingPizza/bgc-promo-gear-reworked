package ca.bgcengineering.promogearreworked.api.products.variant.secured.dto.validation.uniqueoptionset;

import ca.bgcengineering.promogearreworked.api.products.variant.secured.dto.SecuredProductVariantUpdate;
import ca.bgcengineering.promogearreworked.persistence.repositories.ProductRepository;
import ca.bgcengineering.promogearreworked.persistence.repositories.OptionValueRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class VariantUpdateUniqueOptionSetValidator extends UniqueOptionSetValidator
        implements ConstraintValidator<UniqueOptionSet, SecuredProductVariantUpdate> {

    @Autowired
    private ProductRepository productRepo;

    @Autowired
    private OptionValueRepository optionValueRepo;

    @Override
    public boolean isValid(SecuredProductVariantUpdate productVariantUpdate, ConstraintValidatorContext constraintValidatorContext) {
        return validate(productVariantUpdate.getProductId(),
                productVariantUpdate.getOptionValueIds(),
                productRepo,
                optionValueRepo);
    }
}
