package ca.bgcengineering.promogearreworked.api.categories.category.secured.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class SecuredCategoryResponse {

    @Getter
    @RequiredArgsConstructor
    public static class NestedCategory {
        private final Long id;
        private final String name;
    }

    private final Long id;
    private final String name;
    private final NestedCategory parent;
    private final List<NestedCategory> subcategories;

}
