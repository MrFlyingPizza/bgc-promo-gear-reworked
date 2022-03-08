package com.example.bgcpromogearreworked.api.shared.validation.category.validators;

import com.example.bgcpromogearreworked.api.categories.category.dto.secured.SecuredCategoryUpdate;
import com.example.bgcpromogearreworked.api.shared.validation.category.annotations.CategoryParentNotSelf;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CategoryUpdateParentNotSelfValidator implements ConstraintValidator<CategoryParentNotSelf, SecuredCategoryUpdate> {
    @Override
    public boolean isValid(SecuredCategoryUpdate categoryUpdate, ConstraintValidatorContext constraintValidatorContext) {
        if (categoryUpdate.getParentId() == null) {
            return true;
        }
        return CategoryValidatorHelper.validateParentNotSelf(categoryUpdate.getId(), categoryUpdate.getParentId());
    }
}
