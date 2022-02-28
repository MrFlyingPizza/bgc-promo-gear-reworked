package com.example.bgcpromogearreworked.api.categories.category;

import com.example.bgcpromogearreworked.api.categories.category.dto.secured.*;
import com.example.bgcpromogearreworked.api.categories.category.exceptions.CategoryNotFoundException;
import com.example.bgcpromogearreworked.api.categories.category.persistence.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/secured/categories", produces = MediaType.APPLICATION_JSON_VALUE)
public class SecuredCategoryController {

    private final CategoryService categoryService;
    private final SecuredCategoryMapper mapper;

    @PostMapping
    private CategoryResponseDto createCategory(@RequestBody CategoryCreateDto categoryCreateDto) {
        Category result = categoryService.handleCategoryCreate(categoryCreateDto);
        return mapper.toResponseDto(result);
    }

    @GetMapping("/{categoryId}")
    private CategoryResponseDto getCategory(@PathVariable Long categoryId) throws CategoryNotFoundException {
        if (!categoryService.checkCategoryExists(categoryId)) {
            throw new CategoryNotFoundException();
        }
        return mapper.toResponseDto(categoryService.handleCategoryGet(categoryId));
    }

    @GetMapping
    private CategoryBatchResponseDto getCategoryBatch() {
        return mapper.toBatchResponseDto(categoryService.handleCategoryBatchGet());
    }

    @PutMapping("/{categoryId}")
    private CategoryResponseDto updateCategory(@PathVariable Long categoryId,
                                               @RequestBody CategoryUpdateDto categoryUpdateDto) throws CategoryNotFoundException {
        if (!categoryService.checkCategoryExists(categoryId)) {
            throw new CategoryNotFoundException();
        }
        return mapper.toResponseDto(categoryService.handleCategoryUpdate(categoryId, categoryUpdateDto));
    }

}
