package com.example.bgcpromogearreworked.api.common.validation;

import com.example.bgcpromogearreworked.api.categories.category.persistence.Category;
import com.example.bgcpromogearreworked.api.categories.category.persistence.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

public class ValidParentCategoryValidator implements ConstraintValidator<ValidParentCategory, Long> {

    @Autowired
    private CategoryRepository categoryRepo;

    @Override
    public boolean isValid(Long parentCategoryId, ConstraintValidatorContext constraintValidatorContext) {
        if (parentCategoryId == null) return true;
        Optional<Category> optional = categoryRepo.findById(parentCategoryId);
        if (optional.isEmpty()) {
            return false;
        }
        return optional.get().getParent() == null;
    }
}
