package ca.bgcengineering.promogearreworked.api.products.variant.secured.dto.validation.uniqueoptionset;

import ca.bgcengineering.promogearreworked.api.products.variant.secured.dto.SecuredProductVariantUpdate;
import ca.bgcengineering.promogearreworked.persistence.entities.ProductVariant;
import ca.bgcengineering.promogearreworked.persistence.repositories.OptionValueRepository;
import ca.bgcengineering.promogearreworked.persistence.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.stream.Collectors;

public class VariantUpdateUniqueOptionSetValidator extends UniqueOptionSetValidator
        implements ConstraintValidator<UniqueOptionSet, SecuredProductVariantUpdate> {

    @Autowired
    private ProductRepository productRepo;

    @Autowired
    private OptionValueRepository optionValueRepo;

    @Override
    public boolean isValid(SecuredProductVariantUpdate variantUpdate, ConstraintValidatorContext constraintValidatorContext) {

        final Long variantId = variantUpdate.getId();
        return validate(variantUpdate.getOptionValueIds().stream().map(id -> optionValueRepo.getById(id)).collect(Collectors.toSet()),
                productRepo.getById(variantUpdate.getProductId()).getVariants()
                        .stream()
                        .filter(iterVariant -> !iterVariant.getId().equals(variantId))
                        .map(ProductVariant::getOptionValues)
                        .collect(Collectors.toList()));
    }
}
