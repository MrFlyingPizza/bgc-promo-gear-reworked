package ca.bgcengineering.promogearreworked.api.categories.category.secured.dto.validation.parentnotself;

import ca.bgcengineering.promogearreworked.api.categories.category.secured.dto.SecuredCategoryUpdate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CategoryUpdateParentNotSelfValidator extends ParentNotSelfValidator
        implements ConstraintValidator<CategoryParentNotSelf, SecuredCategoryUpdate> {
    @Override
    public boolean isValid(SecuredCategoryUpdate categoryUpdate, ConstraintValidatorContext constraintValidatorContext) {
        if (categoryUpdate.getParentId() == null) {
            return true;
        }
        return validate(categoryUpdate.getId(), categoryUpdate.getParentId());
    }
}
