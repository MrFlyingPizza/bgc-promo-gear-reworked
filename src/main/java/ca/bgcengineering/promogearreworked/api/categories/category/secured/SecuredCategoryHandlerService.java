package ca.bgcengineering.promogearreworked.api.categories.category.secured;

import ca.bgcengineering.promogearreworked.api.categories.category.CategoryService;
import ca.bgcengineering.promogearreworked.api.categories.category.secured.dto.SecuredCategoryCreate;
import ca.bgcengineering.promogearreworked.api.categories.category.secured.dto.SecuredCategoryPartialUpdate;
import ca.bgcengineering.promogearreworked.api.categories.category.secured.dto.SecuredCategoryUpdate;
import ca.bgcengineering.promogearreworked.api.categories.category.secured.dto.SecuredCategoryMapper;
import ca.bgcengineering.promogearreworked.persistence.entities.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@Validated
@Service
public class SecuredCategoryHandlerService {

    private final CategoryService categoryService;
    private final SecuredCategoryMapper mapper;

    Category handleCategoryCreate(@Valid SecuredCategoryCreate categoryCreate) {
        return categoryService.createCategory(categoryCreate, mapper::fromCreate);
    }

    Category handleCategoryGet(Long categoryId) {
        return categoryService.getCategory(categoryId);
    }

    List<Category> handleCategoryBatchGet() {
        return categoryService.getCategories();
    }

    Category handleCategoryUpdate(@Valid SecuredCategoryUpdate categoryUpdate) {
        return categoryService.updateCategory(categoryUpdate.getId(), categoryUpdate, mapper::fromUpdate);
    }

    Category handleCategoryPartialUpdate(@Valid SecuredCategoryPartialUpdate categoryPartialUpdate) {
        // TODO: 2022-03-22 category parentId null has no effect even though category parent can be null
        return categoryService.updateCategory(categoryPartialUpdate.getId(), categoryPartialUpdate, mapper::fromPartialUpdate);
    }

    void handleCategoryDelete(Long categoryId) {
        categoryService.deleteCategory(categoryId);
    }

}
