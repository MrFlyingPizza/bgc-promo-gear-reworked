package com.example.bgcpromogearreworked.api.categories.category.secured.dto.validation.nameuniqueforparent;

import com.example.bgcpromogearreworked.persistence.repositories.CategoryRepository;

public abstract class NameUniqueForParentValidator {
    protected static boolean validate(String name, Long id, CategoryRepository repo) {
        return !repo.existsByNameAndParentId(name, id);
    }
}
