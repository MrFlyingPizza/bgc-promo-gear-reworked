package com.example.bgcpromogearreworked.api.categories.category.dto.secured.validation.parentnotchild;

import com.example.bgcpromogearreworked.api.categories.category.dto.secured.SecuredCategoryCreate;
import com.example.bgcpromogearreworked.persistence.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CategoryCreateParentNotChildValidator extends ParentNotChildValidator
        implements ConstraintValidator<CategoryParentNotChild, SecuredCategoryCreate> {

    @Autowired
    private CategoryRepository repo;

    @Override
    public boolean isValid(SecuredCategoryCreate categoryCreate, ConstraintValidatorContext constraintValidatorContext) {
        return validate(categoryCreate.getParentId(), repo);
    }
}
