package ca.bgcengineering.promogearreworked.api.categories.category.secured.dto.validation.parentnotchild;

import ca.bgcengineering.promogearreworked.persistence.repositories.CategoryRepository;

public abstract class ParentNotChildValidator {
    protected static boolean validate(Long parentId, CategoryRepository repo) {
        if (parentId == null) return true;
        return repo.getById(parentId).getParent() == null;
    }
}
