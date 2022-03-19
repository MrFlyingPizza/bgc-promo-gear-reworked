package com.example.bgcpromogearreworked.api.categories.category.general.dto;

import com.example.bgcpromogearreworked.persistence.entities.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class GeneralCategoryMapper {

    public abstract GeneralCategoryResponse toResponse(Category category);

    public GeneralCategoryBatchResponse toBatchResponse(List<Category> categories) {
        return new GeneralCategoryBatchResponse(categories.stream().map(this::toResponse).collect(Collectors.toList()));
    }

}
