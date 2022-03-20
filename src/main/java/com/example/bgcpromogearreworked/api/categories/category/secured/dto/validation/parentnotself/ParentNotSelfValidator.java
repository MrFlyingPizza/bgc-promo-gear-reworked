package com.example.bgcpromogearreworked.api.categories.category.secured.dto.validation.parentnotself;

public abstract class ParentNotSelfValidator {

    protected static boolean validate(Long id, Long parentId) {
        if (parentId == null) {
            return true;
        }
        return !id.equals(parentId);
    }

}
