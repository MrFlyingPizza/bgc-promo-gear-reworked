package com.example.bgcpromogearreworked.api.shared.validation.category.validators;

import com.example.bgcpromogearreworked.api.categories.persistence.CategoryRepository;

public class CategoryValidatorHelper {

    static boolean validateParentNotChild(Long parentId, CategoryRepository repo) {
        if (parentId == null) {
            return true;
        }
        return repo.getById(parentId).getParent() == null;
    }

    static boolean validateNameUniqueForParent(String name, Long id, CategoryRepository repo) {
        return !repo.existsByNameAndParentId(name, id);
    }

    static boolean validateParentNotSelf(Long id, Long parentId) {
        if (parentId == null) {
            return true;
        }
        return !id.equals(parentId);
    }

}
