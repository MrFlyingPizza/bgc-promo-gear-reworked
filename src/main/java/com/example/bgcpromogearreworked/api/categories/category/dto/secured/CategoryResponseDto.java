package com.example.bgcpromogearreworked.api.categories.category.dto.secured;

import com.example.bgcpromogearreworked.api.categories.category.persistence.Category;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class CategoryResponseDto {

    @Getter
    private static class NestedCategoryDto {
        private final Long id;
        private final String name;

        NestedCategoryDto(Category category) {
            this.id = category.getId();
            this.name = category.getName();
        }
    }

    private final Long id;
    private final String name;
    private final NestedCategoryDto parent;
    private final List<NestedCategoryDto> subcategories;

    CategoryResponseDto(Category category) {
        this.id = category.getId();
        this.name = category.getName();
        this.parent = new NestedCategoryDto(category.getParent());
        this.subcategories = category.getSubCategories().stream().map(NestedCategoryDto::new).collect(Collectors.toList());
    }
}
