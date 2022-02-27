package com.example.bgcpromogearreworked.api.validation;

import com.example.bgcpromogearreworked.api.product.ProductRepository;
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
