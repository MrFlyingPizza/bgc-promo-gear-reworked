package ca.bgcengineering.promogearreworked.api.categories.category.secured.dto.validation.nameuniqueforparent;

import ca.bgcengineering.promogearreworked.api.categories.category.secured.dto.SecuredCategoryUpdate;
import ca.bgcengineering.promogearreworked.persistence.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CategoryUpdateNameUniqueForParentValidator extends NameUniqueForParentValidator implements ConstraintValidator<CategoryNameUniqueForParent, SecuredCategoryUpdate> {

    @Autowired
    private CategoryRepository repo;

    @Override
    public boolean isValid(SecuredCategoryUpdate categoryUpdate, ConstraintValidatorContext constraintValidatorContext) {
        return validate(categoryUpdate.getName(), categoryUpdate.getParentId(), repo);
    }
}
