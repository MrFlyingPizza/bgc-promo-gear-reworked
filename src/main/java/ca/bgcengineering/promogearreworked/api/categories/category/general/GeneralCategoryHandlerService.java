package ca.bgcengineering.promogearreworked.api.categories.category.general;

import ca.bgcengineering.promogearreworked.api.categories.category.CategoryService;
import ca.bgcengineering.promogearreworked.persistence.entities.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GeneralCategoryHandlerService {

    private final CategoryService categoryService;

    Category handleCategoryGet(Long categoryId) {
        return categoryService.getCategory(categoryId);
    }

    List<Category> handleCategoryBatchGet() {
        return categoryService.getCategories();
    }

}
