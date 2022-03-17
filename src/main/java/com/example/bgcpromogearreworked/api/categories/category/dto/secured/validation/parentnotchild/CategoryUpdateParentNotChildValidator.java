package com.example.bgcpromogearreworked.api.categories.category.dto.secured.validation.parentnotchild;

import com.example.bgcpromogearreworked.api.categories.category.dto.secured.SecuredCategoryUpdate;
import com.example.bgcpromogearreworked.persistence.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CategoryUpdateParentNotChildValidator extends ParentNotChildValidator
        implements ConstraintValidator<CategoryParentNotChild, SecuredCategoryUpdate> {

    @Autowired
    private CategoryRepository repo;

    @Override
    public boolean isValid(SecuredCategoryUpdate categoryUpdate, ConstraintValidatorContext constraintValidatorContext) {
        return validate(categoryUpdate.getParentId(), repo);
    }
}
