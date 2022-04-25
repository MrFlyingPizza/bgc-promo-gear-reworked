package ca.bgcengineering.promogearreworked.api.products.variant.secured.dto.validation.uniqueoptionset;

import ca.bgcengineering.promogearreworked.api.products.variant.secured.dto.SecuredProductVariantCreate;
import ca.bgcengineering.promogearreworked.persistence.entities.ProductVariant;
import ca.bgcengineering.promogearreworked.persistence.repositories.OptionValueRepository;
import ca.bgcengineering.promogearreworked.persistence.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.stream.Collectors;

public class VariantCreateUniqueOptionSetValidator extends UniqueOptionSetValidator
        implements ConstraintValidator<UniqueOptionSet, SecuredProductVariantCreate> {

    @Autowired
    private ProductRepository productRepo;

    @Autowired
    private OptionValueRepository optionValueRepo;

    @Override
    public boolean isValid(SecuredProductVariantCreate variantCreate, ConstraintValidatorContext constraintValidatorContext) {
        return validate(variantCreate.getOptionValueIds().stream().map(id -> optionValueRepo.getById(id)).collect(Collectors.toSet()),
                productRepo.getById(variantCreate.getProductId()).getVariants().stream().map(ProductVariant::getOptionValues).collect(Collectors.toList()));
    }
}
