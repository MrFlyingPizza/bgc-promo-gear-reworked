package com.example.bgcpromogearreworked.api.common.validation.category.validators;

import com.example.bgcpromogearreworked.api.categories.category.dto.secured.CategoryCreate;
import com.example.bgcpromogearreworked.api.categories.category.persistence.CategoryRepository;
import com.example.bgcpromogearreworked.api.common.validation.category.annotations.CategoryParentNotChild;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CategoryCreateParentNotChildValidator implements ConstraintValidator<CategoryParentNotChild, CategoryCreate> {

    @Autowired
    private CategoryRepository repo;

    @Override
    public boolean isValid(CategoryCreate categoryCreate, ConstraintValidatorContext constraintValidatorContext) {
        return CategoryValidatorHelper.validateParentNotChild(categoryCreate.getParentId(), repo);
    }
}
