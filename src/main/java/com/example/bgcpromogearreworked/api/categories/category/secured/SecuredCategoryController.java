package com.example.bgcpromogearreworked.api.categories.category.secured;

import com.example.bgcpromogearreworked.api.categories.category.CategoryService;
import com.example.bgcpromogearreworked.api.categories.category.secured.dto.*;
import com.example.bgcpromogearreworked.api.categories.exceptions.CategoryNotFoundException;
import com.example.bgcpromogearreworked.api.categories.exceptions.CategoryStillReferencedException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/secured/categories", produces = MediaType.APPLICATION_JSON_VALUE)
public class SecuredCategoryController {

    private final CategoryService service;
    private final SecuredCategoryHandlerService handlerService;
    private final SecuredCategoryMapper mapper;

    @PostMapping
    private SecuredCategoryResponse createCategory(@RequestBody SecuredCategoryCreate categoryCreate) {
        return mapper.toResponse(handlerService.handleCategoryCreate(categoryCreate));
    }

    @GetMapping("/{categoryId}")
    private SecuredCategoryResponse getCategory(@PathVariable Long categoryId) {
        if (!service.checkCategoryExists(categoryId)) {
            throw new CategoryNotFoundException();
        }
        return mapper.toResponse(handlerService.handleCategoryGet(categoryId));
    }

    @GetMapping
    private SecuredCategoryBatchResponse getCategoryBatch() {
        return mapper.toBatchResponse(handlerService.handleCategoryBatchGet());
    }

    @PatchMapping("/{categoryId}")
    private SecuredCategoryResponse updateCategoryPartial(@PathVariable Long categoryId,
                                                          @RequestBody SecuredCategoryPartialUpdate categoryPartialUpdate) {
        if (!service.checkCategoryExists(categoryId)) {
            throw new CategoryNotFoundException();
        }
        categoryPartialUpdate.setId(categoryId);
        return mapper.toResponse(handlerService.handleCategoryPartialUpdate(categoryPartialUpdate));
    }

    @PutMapping("/{categoryId}")
    private SecuredCategoryResponse updateCategory(@PathVariable Long categoryId,
                                                   @RequestBody SecuredCategoryUpdate categoryUpdate) {
        if (!service.checkCategoryExists(categoryId)) {
            throw new CategoryNotFoundException();
        }
        categoryUpdate.setId(categoryId);
        return mapper.toResponse(handlerService.handleCategoryUpdate(categoryUpdate));
    }

    @DeleteMapping("/{categoryId}")
    private void deleteCategory(@PathVariable Long categoryId) {
        if (!service.checkCategoryExists(categoryId)) {
            throw new CategoryNotFoundException();
        }
        if (service.checkCategoryReferenced(categoryId)) {
            throw new CategoryStillReferencedException();
        }
        handlerService.handleCategoryDelete(categoryId);
    }

}
