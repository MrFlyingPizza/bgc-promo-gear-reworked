package com.example.bgcpromogearreworked.api.categories.category.dto.secured.validation.parentnotself;

public abstract class ParentNotSelfValidator {

    protected boolean validate(Long id, Long parentId) {
        if (parentId == null) {
            return true;
        }
        return !id.equals(parentId);
    }

}
