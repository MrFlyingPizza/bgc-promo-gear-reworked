package com.example.bgcpromogearreworked.api.common.validation.category.validators;

import com.example.bgcpromogearreworked.api.categories.category.persistence.CategoryRepository;
import com.example.bgcpromogearreworked.api.common.validation.category.annotations.CategoryExists;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CategoryExistsValidator implements ConstraintValidator<CategoryExists, Long> {

    @Autowired
    private CategoryRepository repo;

    @Override
    public boolean isValid(Long categoryId, ConstraintValidatorContext constraintValidatorContext) {
        if (categoryId == null) return true;
        return repo.existsById(categoryId);
    }
}
