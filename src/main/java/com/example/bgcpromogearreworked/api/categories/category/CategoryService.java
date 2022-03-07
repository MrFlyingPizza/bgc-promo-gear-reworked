package com.example.bgcpromogearreworked.api.categories.category;

import com.example.bgcpromogearreworked.api.categories.category.dto.secured.CategoryCreate;
import com.example.bgcpromogearreworked.api.categories.category.dto.secured.CategoryPartialUpdate;
import com.example.bgcpromogearreworked.api.categories.category.dto.secured.CategoryUpdate;
import com.example.bgcpromogearreworked.api.categories.category.dto.secured.SecuredCategoryMapper;
import com.example.bgcpromogearreworked.api.categories.category.exceptions.CategoryNotFoundException;
import com.example.bgcpromogearreworked.api.categories.category.persistence.Category;
import com.example.bgcpromogearreworked.api.categories.category.persistence.CategoryRepository;
import com.example.bgcpromogearreworked.api.products.persistence.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@RequiredArgsConstructor
@Validated
@Service
public class CategoryService {

    private final CategoryRepository categoryRepo;
    private final ProductRepository productRepo;
    private final SecuredCategoryMapper mapper;

    public boolean checkCategoryExists(long categoryId) {
        return categoryRepo.existsById(categoryId);
    }
    public boolean checkCategoryStillReferenced(long categoryId) {
        return productRepo.existsByCategoryId(categoryId);
    }

    Category handleCategoryCreate(@Valid CategoryCreate categoryCreate) {
        return categoryRepo.saveAndFlush(mapper.fromCreate(categoryCreate));
    }

    Category handleCategoryGet(Long categoryId) {
        return categoryRepo.findById(categoryId).orElseThrow(CategoryNotFoundException::new);
    }

    Iterable<Category> handleCategoryBatchGet() {
        return categoryRepo.findAll();
    }

    Category handleCategoryUpdate(@Valid CategoryUpdate categoryUpdate) {
        Long categoryId = categoryUpdate.getId();
        Category category = categoryRepo.findById(categoryId).orElseThrow(CategoryNotFoundException::new);
        return categoryRepo.saveAndFlush(mapper.fromUpdate(categoryUpdate, category));
    }

    Category handleCategoryPartialUpdate(@Valid CategoryPartialUpdate categoryPartialUpdate) {
        Long categoryId = categoryPartialUpdate.getId();
        Category category = categoryRepo.findById(categoryId).orElseThrow(CategoryNotFoundException::new);
        return categoryRepo.saveAndFlush(mapper.fromPartialUpdate(categoryPartialUpdate, category));
    }

    void handleCategoryDelete(Long categoryId) {
        categoryRepo.deleteById(categoryId);
    }

}
