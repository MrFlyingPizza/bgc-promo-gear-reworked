package com.example.bgcpromogearreworked.api.categories.category;

import com.example.bgcpromogearreworked.api.categories.category.dto.secured.*;
import com.example.bgcpromogearreworked.api.categories.category.exceptions.CategoryNotFoundException;
import com.example.bgcpromogearreworked.api.categories.category.exceptions.CategoryStillReferencedException;
import com.example.bgcpromogearreworked.api.categories.category.persistence.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/secured/categories", produces = MediaType.APPLICATION_JSON_VALUE)
public class SecuredCategoryController {

    private final CategoryService service;
    private final SecuredCategoryMapper mapper;

    @PostMapping
    private CategoryResponse createCategory(@RequestBody CategoryCreate categoryCreate) {
        Category result = service.handleCategoryCreate(categoryCreate);
        return mapper.toResponse(result);
    }

    @GetMapping("/{categoryId}")
    private CategoryResponse getCategory(@PathVariable Long categoryId) {
        if (!service.checkCategoryExists(categoryId)) {
            throw new CategoryNotFoundException();
        }
        return mapper.toResponse(service.handleCategoryGet(categoryId));
    }

    @GetMapping
    private CategoryBatchResponse getCategoryBatch() {
        return mapper.toBatchResponse(service.handleCategoryBatchGet());
    }

    @PatchMapping("/{categoryId}")
    private CategoryResponse updateCategoryPartial(@PathVariable Long categoryId,
                                                   @RequestBody CategoryPartialUpdate categoryPartialUpdate) {
        if (!service.checkCategoryExists(categoryId)) {
            throw new CategoryNotFoundException();
        }
        categoryPartialUpdate.setId(categoryId);
        return mapper.toResponse(service.handleCategoryPartialUpdate(categoryPartialUpdate));
    }

    @PutMapping("/{categoryId}")
    private CategoryResponse updateCategory(@PathVariable Long categoryId,
                                            @RequestBody CategoryUpdate categoryUpdate) {
        if (!service.checkCategoryExists(categoryId)) {
            throw new CategoryNotFoundException();
        }
        categoryUpdate.setId(categoryId);
        return mapper.toResponse(service.handleCategoryUpdate(categoryUpdate));
    }

    @DeleteMapping("/{categoryId}")
    private void deleteCategory(@PathVariable Long categoryId) {
        if (!service.checkCategoryExists(categoryId)) {
            throw new CategoryNotFoundException();
        }
        if (service.checkCategoryStillReferenced(categoryId)) {
            throw new CategoryStillReferencedException();
        }
        service.handleCategoryDelete(categoryId);
    }

}
