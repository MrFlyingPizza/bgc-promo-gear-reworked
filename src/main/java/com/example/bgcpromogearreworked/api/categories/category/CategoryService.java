package com.example.bgcpromogearreworked.api.categories.category;

import com.example.bgcpromogearreworked.api.categories.category.dto.secured.CategoryCreateDto;
import com.example.bgcpromogearreworked.api.categories.category.dto.secured.CategoryUpdateDto;
import com.example.bgcpromogearreworked.api.categories.category.dto.secured.SecuredCategoryMapper;
import com.example.bgcpromogearreworked.api.categories.category.exceptions.CategoryNotFoundException;
import com.example.bgcpromogearreworked.api.categories.category.persistence.Category;
import com.example.bgcpromogearreworked.api.categories.category.persistence.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CategoryService {

    private final CategoryRepository categoryRepo;
    private final SecuredCategoryMapper mapper;

    public boolean checkCategoryExists(long categoryId) {
        return categoryRepo.existsById(categoryId);
    }

    Category handleCategoryCreate(CategoryCreateDto categoryCreateDto) {
        return categoryRepo.save(mapper.fromCreateDto(categoryCreateDto));
    }

    Category handleCategoryGet(Long categoryId) throws CategoryNotFoundException {
        return categoryRepo.findById(categoryId).orElseThrow(CategoryNotFoundException::new);
    }

    Iterable<Category> handleCategoryBatchGet() {
        return categoryRepo.findAll();
    }

    Category handleCategoryUpdate(Long categoryId, CategoryUpdateDto categoryUpdateDto) throws CategoryNotFoundException {
        Category category = categoryRepo.findById(categoryId).orElseThrow(CategoryNotFoundException::new);
        category = mapper.fromUpdateDto(categoryUpdateDto, category);
        return categoryRepo.save(category);
    }

}
