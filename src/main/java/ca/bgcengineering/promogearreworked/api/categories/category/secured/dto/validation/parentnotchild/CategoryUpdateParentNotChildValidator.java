package ca.bgcengineering.promogearreworked.api.categories.category.secured.dto.validation.parentnotchild;

import ca.bgcengineering.promogearreworked.api.categories.category.secured.dto.SecuredCategoryUpdate;
import ca.bgcengineering.promogearreworked.persistence.repositories.CategoryRepository;
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
