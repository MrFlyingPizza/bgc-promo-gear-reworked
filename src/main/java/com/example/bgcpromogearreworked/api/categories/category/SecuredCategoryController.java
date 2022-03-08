package com.example.bgcpromogearreworked.api.categories.category;

import com.example.bgcpromogearreworked.api.categories.category.dto.secured.*;
import com.example.bgcpromogearreworked.api.categories.exceptions.CategoryNotFoundException;
import com.example.bgcpromogearreworked.api.categories.exceptions.CategoryStillReferencedException;
import com.example.bgcpromogearreworked.api.categories.persistence.Category;
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
    private SecuredCategoryResponse createCategory(@RequestBody SecuredCategoryCreate categoryCreate) {
        Category result = service.handleCategoryCreate(categoryCreate);
        return mapper.toResponse(result);
    }

    @GetMapping("/{categoryId}")
    private SecuredCategoryResponse getCategory(@PathVariable Long categoryId) {
        if (!service.checkCategoryExists(categoryId)) {
            throw new CategoryNotFoundException();
        }
        return mapper.toResponse(service.handleCategoryGet(categoryId));
    }

    @GetMapping
    private SecuredCategoryBatchResponse getCategoryBatch() {
        return mapper.toBatchResponse(service.handleCategoryBatchGet());
    }

    @PatchMapping("/{categoryId}")
    private SecuredCategoryResponse updateCategoryPartial(@PathVariable Long categoryId,
                                                          @RequestBody SecuredCategoryPartialUpdate categoryPartialUpdate) {
        if (!service.checkCategoryExists(categoryId)) {
            throw new CategoryNotFoundException();
        }
        categoryPartialUpdate.setId(categoryId);
        return mapper.toResponse(service.handleCategoryPartialUpdate(categoryPartialUpdate));
    }

    @PutMapping("/{categoryId}")
    private SecuredCategoryResponse updateCategory(@PathVariable Long categoryId,
                                                   @RequestBody SecuredCategoryUpdate categoryUpdate) {
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
