package com.example.bgcpromogearreworked.api.categories.category.dto.secured;

import com.example.bgcpromogearreworked.persistence.entities.Category;
import com.example.bgcpromogearreworked.persistence.repositories.CategoryRepository;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;

@Mapper(componentModel = "spring")
public abstract class SecuredCategoryMapper {

    @Autowired
    private CategoryRepository categoryRepo;

    @Mapping(source = "parentId", target = "parent")
    @Mapping(target = "subcategories", ignore = true)
    @Mapping(target = "id", ignore = true)
    public abstract Category fromCreate(SecuredCategoryCreate categoryCreate);

    @Mapping(source = "parentId", target = "parent")
    @Mapping(target = "subcategories", ignore = true)
    public abstract Category fromUpdate(SecuredCategoryUpdate categoryUpdate, @MappingTarget Category category);

    @Mapping(source = "parentId", target = "parent")
    @Mapping(target = "subcategories", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
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

    protected Category map(Long categoryId) {
        return categoryRepo.getById(categoryId);
    }
}
