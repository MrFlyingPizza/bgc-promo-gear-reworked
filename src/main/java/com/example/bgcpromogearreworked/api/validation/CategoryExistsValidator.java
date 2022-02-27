package com.example.bgcpromogearreworked.api.validation;

import com.example.bgcpromogearreworked.api.category.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CategoryExistsValidator implements ConstraintValidator<CategoryExists, Long> {

    @Autowired
    private CategoryRepository categoryRepo;

    @Override
    public boolean isValid(Long categoryId, ConstraintValidatorContext constraintValidatorContext) {
        return categoryRepo.existsById(categoryId);
    }
}
