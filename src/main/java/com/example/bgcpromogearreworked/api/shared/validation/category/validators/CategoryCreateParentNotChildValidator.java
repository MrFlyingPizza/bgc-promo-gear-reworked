package com.example.bgcpromogearreworked.api.shared.validation.category.validators;

import com.example.bgcpromogearreworked.api.categories.category.dto.secured.SecuredCategoryCreate;
import com.example.bgcpromogearreworked.api.categories.persistence.CategoryRepository;
import com.example.bgcpromogearreworked.api.shared.validation.category.annotations.CategoryParentNotChild;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CategoryCreateParentNotChildValidator implements ConstraintValidator<CategoryParentNotChild, SecuredCategoryCreate> {

    @Autowired
    private CategoryRepository repo;

    @Override
    public boolean isValid(SecuredCategoryCreate categoryCreate, ConstraintValidatorContext constraintValidatorContext) {
        return CategoryValidatorHelper.validateParentNotChild(categoryCreate.getParentId(), repo);
    }
}
