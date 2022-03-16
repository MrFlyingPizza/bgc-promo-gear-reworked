package com.example.bgcpromogearreworked.api.categories.category;

import com.example.bgcpromogearreworked.api.categories.category.dto.secured.SecuredCategoryCreate;
import com.example.bgcpromogearreworked.api.categories.category.dto.secured.SecuredCategoryPartialUpdate;
import com.example.bgcpromogearreworked.api.categories.category.dto.secured.SecuredCategoryUpdate;
import com.example.bgcpromogearreworked.api.categories.category.dto.secured.SecuredCategoryMapper;
import com.example.bgcpromogearreworked.api.categories.exceptions.CategoryNotFoundException;
import com.example.bgcpromogearreworked.persistence.entities.Category;
import com.example.bgcpromogearreworked.persistence.repositories.CategoryRepository;
import com.example.bgcpromogearreworked.persistence.repositories.ProductRepository;
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

    Category handleCategoryCreate(@Valid SecuredCategoryCreate categoryCreate) {
        return categoryRepo.saveAndFlush(mapper.fromCreate(categoryCreate));
    }

    Category handleCategoryGet(Long categoryId) {
        return categoryRepo.findById(categoryId).orElseThrow(CategoryNotFoundException::new);
    }

    Iterable<Category> handleCategoryBatchGet() {
        return categoryRepo.findAll();
    }

    Category handleCategoryUpdate(@Valid SecuredCategoryUpdate categoryUpdate) {
        Long categoryId = categoryUpdate.getId();
        Category category = categoryRepo.findById(categoryId).orElseThrow(CategoryNotFoundException::new);
        return categoryRepo.saveAndFlush(mapper.fromUpdate(categoryUpdate, category));
    }

    Category handleCategoryPartialUpdate(@Valid SecuredCategoryPartialUpdate categoryPartialUpdate) {
        Long categoryId = categoryPartialUpdate.getId();
        Category category = categoryRepo.findById(categoryId).orElseThrow(CategoryNotFoundException::new);
        return categoryRepo.saveAndFlush(mapper.fromPartialUpdate(categoryPartialUpdate, category));
    }

    void handleCategoryDelete(Long categoryId) {
        categoryRepo.deleteById(categoryId);
    }

}
