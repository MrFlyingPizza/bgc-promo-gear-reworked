package com.example.bgcpromogearreworked.api.shared.validation.category.validators;

import com.example.bgcpromogearreworked.api.categories.category.dto.secured.SecuredCategoryPartialUpdate;
import com.example.bgcpromogearreworked.api.categories.persistence.CategoryRepository;
import com.example.bgcpromogearreworked.api.shared.validation.category.annotations.CategoryParentNotSelf;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CategoryPartialUpdateParentNotSelfValidator implements ConstraintValidator<CategoryParentNotSelf, SecuredCategoryPartialUpdate> {

    @Autowired
    private CategoryRepository repo;

    @Override
    public boolean isValid(SecuredCategoryPartialUpdate categoryPartialUpdate, ConstraintValidatorContext constraintValidatorContext) {
        return CategoryValidatorHelper.validateParentNotSelf(categoryPartialUpdate.getId(), categoryPartialUpdate.getParentId());
    }
}
