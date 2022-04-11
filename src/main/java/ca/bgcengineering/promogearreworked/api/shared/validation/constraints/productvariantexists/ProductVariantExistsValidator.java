package ca.bgcengineering.promogearreworked.api.shared.validation.constraints.productvariantexists;

import ca.bgcengineering.promogearreworked.persistence.repositories.ProductVariantRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ProductVariantExistsValidator implements ConstraintValidator<ProductVariantExists, Long> {

    @Autowired
    private ProductVariantRepository variantRepo;

    @Override
    public boolean isValid(Long variantId, ConstraintValidatorContext context) {
        return variantId == null || variantRepo.existsById(variantId);
    }
}
