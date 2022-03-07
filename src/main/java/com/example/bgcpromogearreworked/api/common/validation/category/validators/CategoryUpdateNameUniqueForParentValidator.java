package com.example.bgcpromogearreworked.api.common.validation.category.validators;

import com.example.bgcpromogearreworked.api.categories.category.dto.secured.CategoryUpdate;
import com.example.bgcpromogearreworked.api.categories.category.persistence.CategoryRepository;
import com.example.bgcpromogearreworked.api.common.validation.category.annotations.CategoryNameUniqueForParent;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CategoryUpdateNameUniqueForParentValidator implements ConstraintValidator<CategoryNameUniqueForParent, CategoryUpdate> {

    @Autowired
    private CategoryRepository repo;

    @Override
    public boolean isValid(CategoryUpdate categoryUpdate, ConstraintValidatorContext constraintValidatorContext) {
        return CategoryValidatorHelper.validateNameUniqueForParent(categoryUpdate.getName(),
                categoryUpdate.getParentId(),
                repo);
    }
}
