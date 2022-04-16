package ca.bgcengineering.promogearreworked.api.categories.category.secured.dto.validation.parentnotself;

import ca.bgcengineering.promogearreworked.api.categories.category.secured.dto.SecuredCategoryPartialUpdate;
import ca.bgcengineering.promogearreworked.persistence.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CategoryPartialUpdateParentNotSelfValidator extends ParentNotSelfValidator
        implements ConstraintValidator<CategoryParentNotSelf, SecuredCategoryPartialUpdate> {

    @Autowired
    private CategoryRepository repo;

    @Override
    public boolean isValid(SecuredCategoryPartialUpdate categoryPartialUpdate, ConstraintValidatorContext constraintValidatorContext) {
        return validate(categoryPartialUpdate.getId(), categoryPartialUpdate.getParentId());
    }
}
