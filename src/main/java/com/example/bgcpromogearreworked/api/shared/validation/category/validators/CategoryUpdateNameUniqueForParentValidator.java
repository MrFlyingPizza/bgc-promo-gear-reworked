package com.example.bgcpromogearreworked.api.shared.validation.category.validators;

import com.example.bgcpromogearreworked.api.categories.category.dto.secured.SecuredCategoryUpdate;
import com.example.bgcpromogearreworked.api.categories.persistence.CategoryRepository;
import com.example.bgcpromogearreworked.api.shared.validation.category.annotations.CategoryNameUniqueForParent;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CategoryUpdateNameUniqueForParentValidator implements ConstraintValidator<CategoryNameUniqueForParent, SecuredCategoryUpdate> {

    @Autowired
    private CategoryRepository repo;

    @Override
    public boolean isValid(SecuredCategoryUpdate categoryUpdate, ConstraintValidatorContext constraintValidatorContext) {
        return CategoryValidatorHelper.validateNameUniqueForParent(categoryUpdate.getName(),
                categoryUpdate.getParentId(),
                repo);
    }
}
