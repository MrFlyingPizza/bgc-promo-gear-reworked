package ca.bgcengineering.promogearreworked.api.categories.category.general.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class GeneralCategoryResponse {

    @Getter
    @RequiredArgsConstructor
    static class NestedCategory {
        private final Long id;
        private final String name;
    }

    private final Long id;
    private final String name;
    private final NestedCategory parent;
    private final List<NestedCategory> subcategories;

}
