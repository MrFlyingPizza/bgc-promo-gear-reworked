package ca.bgcengineering.promogearreworked.api.categories.category.secured.dto.validation.parentnotchild;

import ca.bgcengineering.promogearreworked.api.categories.category.secured.dto.SecuredCategoryPartialUpdate;
import ca.bgcengineering.promogearreworked.persistence.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CategoryPartialUpdateParentNotChildValidator extends ParentNotChildValidator
        implements ConstraintValidator<CategoryParentNotChild, SecuredCategoryPartialUpdate> {

    @Autowired
    private CategoryRepository repo;

    @Override
    public boolean isValid(SecuredCategoryPartialUpdate categoryPartialUpdate, ConstraintValidatorContext constraintValidatorContext) {
        return validate(categoryPartialUpdate.getParentId(), repo);
    }
}
