package com.example.bgcpromogearreworked.api.categories.category.dto.secured;

import com.example.bgcpromogearreworked.api.categories.persistence.Category;
import org.mapstruct.*;
import org.springframework.data.util.Streamable;

@Mapper(componentModel = "spring")
public abstract class SecuredCategoryMapper {

    @Mapping(source = "parentId", target = "parent.id")
    public abstract Category fromCreate(SecuredCategoryCreate categoryCreate);

    @Mapping(source = "parentId", target = "parent.id")
    public abstract Category fromUpdate(SecuredCategoryUpdate categoryUpdate, @MappingTarget Category category);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "parentId", target = "parent.id")
    public abstract Category fromPartialUpdate(SecuredCategoryPartialUpdate categoryPartialUpdate, @MappingTarget Category category);

    public abstract SecuredCategoryResponse toResponse(Category category);

    public SecuredCategoryBatchResponse toBatchResponse(Iterable<Category> categories) {
        return new SecuredCategoryBatchResponse(Streamable.of(categories).map(this::toResponse).toList());
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
