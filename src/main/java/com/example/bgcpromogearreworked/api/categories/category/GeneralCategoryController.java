package com.example.bgcpromogearreworked.api.categories.category;

import com.example.bgcpromogearreworked.api.categories.category.dto.general.GeneralCategoryBatchResponse;
import com.example.bgcpromogearreworked.api.categories.category.dto.general.GeneralCategoryResponse;
import com.example.bgcpromogearreworked.api.categories.category.dto.general.GeneralCategoryMapper;
import com.example.bgcpromogearreworked.api.categories.exceptions.CategoryNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/categories")
public class GeneralCategoryController {

    private final CategoryService service;
    private final GeneralCategoryMapper mapper;

    @GetMapping
    private GeneralCategoryBatchResponse getCategoryBatch() {
        return mapper.toBatchResponse(service.handleCategoryBatchGet());
    }

    @GetMapping("/{categoryId}")
    private GeneralCategoryResponse getCategory(@PathVariable Long categoryId) {
        if (!service.checkCategoryExists(categoryId)) {
            throw new CategoryNotFoundException();
        }
        return mapper.toResponse(service.handleCategoryGet(categoryId));
    }

}
