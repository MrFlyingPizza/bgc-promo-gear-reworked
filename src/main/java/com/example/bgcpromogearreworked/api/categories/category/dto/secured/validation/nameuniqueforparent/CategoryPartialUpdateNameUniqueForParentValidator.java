package com.example.bgcpromogearreworked.api.categories.category.dto.secured.validation.nameuniqueforparent;

import com.example.bgcpromogearreworked.api.categories.category.dto.secured.SecuredCategoryPartialUpdate;
import com.example.bgcpromogearreworked.persistence.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CategoryPartialUpdateNameUniqueForParentValidator extends NameUniqueForParentValidator implements ConstraintValidator<CategoryNameUniqueForParent, SecuredCategoryPartialUpdate> {

    @Autowired
    private CategoryRepository repo;

    @Override
    public boolean isValid(SecuredCategoryPartialUpdate categoryPartialUpdate, ConstraintValidatorContext constraintValidatorContext) {
        return validate(categoryPartialUpdate.getName(), categoryPartialUpdate.getParentId(), repo);
    }

}
