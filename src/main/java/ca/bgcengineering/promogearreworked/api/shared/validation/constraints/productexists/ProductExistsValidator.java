package ca.bgcengineering.promogearreworked.api.shared.validation.constraints.productexists;

import ca.bgcengineering.promogearreworked.persistence.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ProductExistsValidator implements ConstraintValidator<ProductExists, Long> {

    @Autowired
    private ProductRepository productRepo;

    @Override
    public boolean isValid(Long productId, ConstraintValidatorContext constraintValidatorContext) {
        if (productId == null) return true;
        return productRepo.existsById(productId);
    }
}
