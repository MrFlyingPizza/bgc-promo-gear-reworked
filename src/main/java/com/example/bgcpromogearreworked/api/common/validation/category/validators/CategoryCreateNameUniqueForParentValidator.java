package com.example.bgcpromogearreworked.api.common.validation.category.validators;

import com.example.bgcpromogearreworked.api.categories.category.dto.secured.CategoryCreate;
import com.example.bgcpromogearreworked.api.categories.category.persistence.CategoryRepository;
import com.example.bgcpromogearreworked.api.common.validation.category.annotations.CategoryNameUniqueForParent;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CategoryCreateNameUniqueForParentValidator implements ConstraintValidator<CategoryNameUniqueForParent, CategoryCreate> {

    @Autowired
    private CategoryRepository repo;

    @Override
    public boolean isValid(CategoryCreate categoryCreate, ConstraintValidatorContext constraintValidatorContext) {
        return CategoryValidatorHelper.validateNameUniqueForParent(categoryCreate.getName(),
                categoryCreate.getParentId(),
                repo);
    }
}
