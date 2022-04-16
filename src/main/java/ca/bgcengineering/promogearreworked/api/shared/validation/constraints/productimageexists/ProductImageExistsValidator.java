package ca.bgcengineering.promogearreworked.api.shared.validation.constraints.productimageexists;

import ca.bgcengineering.promogearreworked.persistence.repositories.ProductImageRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ProductImageExistsValidator implements ConstraintValidator<ProductImageExists, Long> {

    @Autowired
    private ProductImageRepository imageRepo;

    @Override
    public boolean isValid(Long imageId, ConstraintValidatorContext constraintValidatorContext) {
        if (imageId == null) return true;
        return imageRepo.existsById(imageId);
    }
}
