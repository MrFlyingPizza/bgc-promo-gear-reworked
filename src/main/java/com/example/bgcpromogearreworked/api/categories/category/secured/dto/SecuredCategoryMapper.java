package com.example.bgcpromogearreworked.api.categories.category.secured.dto;

import com.example.bgcpromogearreworked.persistence.entities.Category;
import com.example.bgcpromogearreworked.persistence.repositories.CategoryRepository;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class SecuredCategoryMapper {

    @Autowired
    private CategoryRepository categoryRepo;

    @Mapping(source = "parentId", target = "parent.id")
    @Mapping(target = "subcategories", ignore = true)
    @Mapping(target = "id", ignore = true)
    public abstract Category fromCreate(SecuredCategoryCreate categoryCreate);

    @Mapping(source = "parentId", target = "parent.id")
    @Mapping(target = "subcategories", ignore = true)
    public abstract Category fromUpdate(SecuredCategoryUpdate categoryUpdate, @MappingTarget Category category);

    @Mapping(source = "parentId", target = "parent.id")
    @Mapping(target = "subcategories", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract Category fromPartialUpdate(SecuredCategoryPartialUpdate categoryPartialUpdate, @MappingTarget Category category);

    public abstract SecuredCategoryResponse toResponse(Category category);

    public SecuredCategoryBatchResponse toBatchResponse(List<Category> categories) {
        return new SecuredCategoryBatchResponse(categories.stream().map(this::toResponse).collect(Collectors.toList()));
    }

    @AfterMapping
    protected void mapCategoryFromRepoOrNull(@MappingTarget Category category) {
        if (category.getParent() == null) {
            return;
        }
        Long id = category.getParent().getId();
        if (id == null) {
            category.setParent(null);
        } else {
            category.setParent(categoryRepo.getById(category.getParent().getId()));
        }
    }
}
