package com.example.bgcpromogearreworked.api.categories.category.dto.secured;

import com.example.bgcpromogearreworked.api.categories.category.persistence.Category;
import org.mapstruct.*;
import org.springframework.data.util.Streamable;

@Mapper(componentModel = "spring")
public abstract class SecuredCategoryMapper {

    @Mapping(source = "parentId", target = "parent.id")
    public abstract Category fromCreate(CategoryCreate categoryCreate);

    @Mapping(source = "parentId", target = "parent.id")
    public abstract Category fromUpdate(CategoryUpdate categoryUpdate, @MappingTarget Category category);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "parentId", target = "parent.id")
    public abstract Category fromPartialUpdate(CategoryPartialUpdate categoryPartialUpdate, @MappingTarget Category category);

    @Mapping(source = "subCategories", target = "subcategories")
    public abstract CategoryResponse toResponse(Category category);

    public CategoryBatchResponse toBatchResponse(Iterable<Category> categories) {
        return new CategoryBatchResponse(Streamable.of(categories).map(this::toResponse).toList());
    }

    @AfterMapping
    protected void setParentNullIfNoParentId(@MappingTarget Category category) {
        if (category.getParent() == null) {
            return;
        }
        if (category.getParent().getId() == null) {
            category.setParent(null);
        }
    }
}
