package com.example.bgcpromogearreworked.api.categories.category.dto.secured;

import com.example.bgcpromogearreworked.api.categories.category.persistence.Category;
import lombok.Getter;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class CategoryBatchResponseDto {

    private final List<CategoryResponseDto> categories;

    public CategoryBatchResponseDto(Collection<Category> categories) {
        this.categories = categories.stream().map(CategoryResponseDto::new).collect(Collectors.toList());
    }
}
