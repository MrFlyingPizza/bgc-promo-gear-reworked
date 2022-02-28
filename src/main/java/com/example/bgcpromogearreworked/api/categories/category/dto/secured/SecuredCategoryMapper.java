package com.example.bgcpromogearreworked.api.categories.category.dto.secured;

import com.example.bgcpromogearreworked.api.categories.category.persistence.Category;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.data.util.Streamable;

@Mapper(componentModel = "spring")
public interface SecuredCategoryMapper {

    Category fromCreateDto(CategoryCreateDto categoryCreateDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Category fromUpdateDto(CategoryUpdateDto categoryUpdateDto, @MappingTarget Category category);

    default CategoryResponseDto toResponseDto(Category category) {
        return new CategoryResponseDto(category);
    }

    default CategoryBatchResponseDto toBatchResponseDto(Iterable<Category> categories) {
        return new CategoryBatchResponseDto(Streamable.of(categories).toList());
    }

}
