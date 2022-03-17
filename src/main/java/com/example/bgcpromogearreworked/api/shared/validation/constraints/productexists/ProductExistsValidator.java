package com.example.bgcpromogearreworked.api.shared.validation.constraints.productexists;

import com.example.bgcpromogearreworked.persistence.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ProductExistsValidator implements ConstraintValidator<ProductExists, Long> {

    @Autowired
    private ProductRepository productRepo;

    @Override
    public boolean isValid(Long productId, ConstraintValidatorContext constraintValidatorContext) {
        return productRepo.existsById(productId);
    }
}
