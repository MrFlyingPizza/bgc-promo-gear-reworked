package ca.bgcengineering.promogearreworked.api.categories.category;

import ca.bgcengineering.promogearreworked.api.categories.exceptions.CategoryNotFoundException;
import ca.bgcengineering.promogearreworked.persistence.entities.Category;
import ca.bgcengineering.promogearreworked.persistence.repositories.CategoryRepository;
import ca.bgcengineering.promogearreworked.persistence.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepo;
    private final ProductRepository productRepo;

    public boolean checkCategoryExists(Long categoryId) {
        assert categoryId != null;
        return categoryRepo.existsById(categoryId);
    }

    public boolean checkCategoryReferenced(Long categoryId) {
        assert categoryId != null;
        return productRepo.existsByCategoryId(categoryId);
    }

    public Category getCategory(Long categoryId) {
        assert categoryId != null;
        return categoryRepo.findById(categoryId).orElseThrow(CategoryNotFoundException::new);
    }

    public List<Category> getCategories() {
        return categoryRepo.findAll();
    }

    public <T> Category createCategory(T source, Function<T, Category> mapper) {
        assert source != null && mapper != null;
        Category category = mapper.apply(source);
        assert category.getId() == null;
        return categoryRepo.saveAndFlush(category);
    }

    public <T> Category updateCategory(Long categoryId, T source, BiFunction<T, Category, Category> mapper) {
        assert categoryId != null && source != null && mapper != null;
        Category category = mapper.apply(source, categoryRepo.findById(categoryId).orElseThrow(CategoryNotFoundException::new));
        assert category.getId().equals(categoryId);
        return categoryRepo.saveAndFlush(category);
    }

    public void deleteCategory(Long categoryId) {
        assert categoryId != null;
        categoryRepo.deleteById(categoryId);
    }
}
