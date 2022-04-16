package ca.bgcengineering.promogearreworked.api.categories.category.secured.dto.validation.nameuniqueforparent;

import ca.bgcengineering.promogearreworked.persistence.repositories.CategoryRepository;

public abstract class NameUniqueForParentValidator {
    protected static boolean validate(String name, Long id, CategoryRepository repo) {
        assert id != null;
        if (name == null) return true;
        return !repo.existsByNameAndParentId(name, id);
    }
}
