package ca.bgcengineering.promogearreworked.api.categories.category.secured.dto.validation.nameuniqueforparent;

import ca.bgcengineering.promogearreworked.api.categories.category.secured.dto.SecuredCategoryCreate;
import ca.bgcengineering.promogearreworked.persistence.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CategoryCreateNameUniqueForParentValidator
        extends NameUniqueForParentValidator
        implements ConstraintValidator<CategoryNameUniqueForParent, SecuredCategoryCreate> {

    @Autowired
    private CategoryRepository repo;

    @Override
    public boolean isValid(SecuredCategoryCreate categoryCreate, ConstraintValidatorContext constraintValidatorContext) {
        return validate(categoryCreate.getName(), categoryCreate.getParentId(), repo);
    }
}
