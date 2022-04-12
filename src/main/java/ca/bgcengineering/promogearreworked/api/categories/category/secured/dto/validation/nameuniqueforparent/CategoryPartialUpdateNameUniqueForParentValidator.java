package ca.bgcengineering.promogearreworked.api.categories.category.secured.dto.validation.nameuniqueforparent;

import ca.bgcengineering.promogearreworked.api.categories.category.secured.dto.SecuredCategoryPartialUpdate;
import ca.bgcengineering.promogearreworked.persistence.repositories.CategoryRepository;
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
