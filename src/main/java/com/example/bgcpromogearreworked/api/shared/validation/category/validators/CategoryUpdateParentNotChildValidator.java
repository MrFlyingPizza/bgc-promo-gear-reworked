package com.example.bgcpromogearreworked.api.shared.validation.category.validators;

import com.example.bgcpromogearreworked.api.categories.category.dto.secured.SecuredCategoryUpdate;
import com.example.bgcpromogearreworked.api.categories.persistence.CategoryRepository;
import com.example.bgcpromogearreworked.api.shared.validation.category.annotations.CategoryParentNotChild;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CategoryUpdateParentNotChildValidator implements ConstraintValidator<CategoryParentNotChild, SecuredCategoryUpdate> {

    @Autowired
    private CategoryRepository repo;

    @Override
    public boolean isValid(SecuredCategoryUpdate categoryUpdate, ConstraintValidatorContext constraintValidatorContext) {
        return CategoryValidatorHelper.validateParentNotChild(categoryUpdate.getParentId(), repo);
    }
}
