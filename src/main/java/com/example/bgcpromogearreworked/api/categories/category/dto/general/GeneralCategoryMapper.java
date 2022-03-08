package com.example.bgcpromogearreworked.api.categories.category.dto.general;

import com.example.bgcpromogearreworked.api.categories.persistence.Category;
import org.mapstruct.Mapper;
import org.springframework.data.util.Streamable;

@Mapper(componentModel = "spring")
public abstract class GeneralCategoryMapper {

    public abstract GeneralCategoryResponse toResponse(Category category);

    public GeneralCategoryBatchResponse toBatchResponse(Iterable<Category> categories) {
        return new GeneralCategoryBatchResponse(Streamable.of(categories).map(this::toResponse).toList());
    }

}
