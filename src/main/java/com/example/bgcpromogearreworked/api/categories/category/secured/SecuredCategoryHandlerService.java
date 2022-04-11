package com.example.bgcpromogearreworked.api.categories.category.secured;

import com.example.bgcpromogearreworked.api.categories.category.CategoryService;
import com.example.bgcpromogearreworked.api.categories.category.secured.dto.SecuredCategoryCreate;
import com.example.bgcpromogearreworked.api.categories.category.secured.dto.SecuredCategoryPartialUpdate;
import com.example.bgcpromogearreworked.api.categories.category.secured.dto.SecuredCategoryUpdate;
import com.example.bgcpromogearreworked.api.categories.category.secured.dto.SecuredCategoryMapper;
import com.example.bgcpromogearreworked.persistence.entities.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@Validated
@Service
public class SecuredCategoryHandlerService {

    private final CategoryService categoryService;
    private final SecuredCategoryMapper mapper;

    Category handleCategoryCreate(@Valid SecuredCategoryCreate categoryCreate) {
        return categoryService.createCategory(categoryCreate, mapper::fromCreate);
    }

    Category handleCategoryGet(Long categoryId) {
        return categoryService.getCategory(categoryId);
    }

    List<Category> handleCategoryBatchGet() {
        return categoryService.getCategories();
    }

    Category handleCategoryUpdate(@Valid SecuredCategoryUpdate categoryUpdate) {
        return categoryService.updateCategory(categoryUpdate.getId(), categoryUpdate, mapper::fromUpdate);
    }

    Category handleCategoryPartialUpdate(@Valid SecuredCategoryPartialUpdate categoryPartialUpdate) {
        // TODO: 2022-03-22 category parentId null has no effect even though category parent can be null
        return categoryService.updateCategory(categoryPartialUpdate.getId(), categoryPartialUpdate, mapper::fromPartialUpdate);
    }

    void handleCategoryDelete(Long categoryId) {
        categoryService.deleteCategory(categoryId);
    }

}
