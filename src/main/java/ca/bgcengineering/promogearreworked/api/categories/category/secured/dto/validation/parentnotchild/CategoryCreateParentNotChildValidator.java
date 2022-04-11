package ca.bgcengineering.promogearreworked.api.categories.category.secured.dto.validation.parentnotchild;

import ca.bgcengineering.promogearreworked.api.categories.category.secured.dto.SecuredCategoryCreate;
import ca.bgcengineering.promogearreworked.persistence.repositories.CategoryRepository;
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
