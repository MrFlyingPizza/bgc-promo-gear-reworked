package com.example.bgcpromogearreworked.api.common.validation.category.validators;

import com.example.bgcpromogearreworked.api.categories.category.dto.secured.CategoryPartialUpdate;
import com.example.bgcpromogearreworked.api.categories.category.persistence.CategoryRepository;
import com.example.bgcpromogearreworked.api.common.validation.category.annotations.CategoryNameUniqueForParent;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CategoryPartialUpdateNameUniqueForParentValidator implements ConstraintValidator<CategoryNameUniqueForParent, CategoryPartialUpdate> {

    @Autowired
    private CategoryRepository repo;

    @Override
    public boolean isValid(CategoryPartialUpdate categoryPartialUpdate, ConstraintValidatorContext constraintValidatorContext) {
        return CategoryValidatorHelper.validateNameUniqueForParent(categoryPartialUpdate.getName(),
                categoryPartialUpdate.getParentId(),
                repo);
    }

}
