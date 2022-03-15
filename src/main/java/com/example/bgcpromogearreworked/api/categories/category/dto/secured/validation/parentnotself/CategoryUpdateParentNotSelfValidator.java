package com.example.bgcpromogearreworked.api.categories.category.dto.secured.validation.parentnotself;

import com.example.bgcpromogearreworked.api.categories.category.dto.secured.SecuredCategoryUpdate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CategoryUpdateParentNotSelfValidator extends ParentNotSelfValidator
        implements ConstraintValidator<CategoryParentNotSelf, SecuredCategoryUpdate> {
    @Override
    public boolean isValid(SecuredCategoryUpdate categoryUpdate, ConstraintValidatorContext constraintValidatorContext) {
        if (categoryUpdate.getParentId() == null) {
            return true;
        }
        return validate(categoryUpdate.getId(), categoryUpdate.getParentId());
    }
}
