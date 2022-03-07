package com.example.bgcpromogearreworked.api.categories.category.dto.general;

import com.example.bgcpromogearreworked.api.categories.category.persistence.Category;
import org.mapstruct.Mapper;
import org.springframework.data.util.Streamable;

@Mapper(componentModel = "spring")
public abstract class GeneralCategoryMapper {

    public abstract CategoryResponse toResponse(Category category);

    public CategoryBatchResponse toBatchResponse(Iterable<Category> categories) {
        return new CategoryBatchResponse(Streamable.of(categories).map(this::toResponse).toList());
    }

}
