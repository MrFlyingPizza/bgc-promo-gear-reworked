package com.example.bgcpromogearreworked.api.common.validation.category.validators;

import com.example.bgcpromogearreworked.api.categories.category.dto.secured.CategoryUpdate;
import com.example.bgcpromogearreworked.api.common.validation.category.annotations.CategoryParentNotSelf;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CategoryUpdateParentNotSelfValidator implements ConstraintValidator<CategoryParentNotSelf, CategoryUpdate> {
    @Override
    public boolean isValid(CategoryUpdate categoryUpdate, ConstraintValidatorContext constraintValidatorContext) {
        if (categoryUpdate.getParentId() == null) {
            return true;
        }
        return CategoryValidatorHelper.validateParentNotSelf(categoryUpdate.getId(), categoryUpdate.getParentId());
    }
}
