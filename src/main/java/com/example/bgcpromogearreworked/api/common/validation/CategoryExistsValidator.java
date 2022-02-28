package com.example.bgcpromogearreworked.api.common.validation;

import com.example.bgcpromogearreworked.api.categories.category.persistence.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CategoryExistsValidator implements ConstraintValidator<CategoryExists, Long> {

    @Autowired
    private CategoryRepository categoryRepo;

    @Override
    public boolean isValid(Long categoryId, ConstraintValidatorContext constraintValidatorContext) {
        if (categoryId == null) return true;
        return categoryRepo.existsById(categoryId);
    }
}
