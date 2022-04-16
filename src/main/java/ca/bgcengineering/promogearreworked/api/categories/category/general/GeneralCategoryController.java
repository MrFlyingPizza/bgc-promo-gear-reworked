package ca.bgcengineering.promogearreworked.api.categories.category.general;

import ca.bgcengineering.promogearreworked.api.categories.category.general.dto.GeneralCategoryResponse;
import ca.bgcengineering.promogearreworked.api.categories.category.CategoryService;
import ca.bgcengineering.promogearreworked.api.categories.category.general.dto.GeneralCategoryBatchResponse;
import ca.bgcengineering.promogearreworked.api.categories.category.general.dto.GeneralCategoryMapper;
import ca.bgcengineering.promogearreworked.api.categories.exceptions.CategoryNotFoundException;
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
    private final GeneralCategoryHandlerService handlerService;
    private final GeneralCategoryMapper mapper;

    @GetMapping
    private GeneralCategoryBatchResponse getCategoryBatch() {
        return mapper.toBatchResponse(handlerService.handleCategoryBatchGet());
    }

    @GetMapping("/{categoryId}")
    private GeneralCategoryResponse getCategory(@PathVariable Long categoryId) {
        if (!service.checkCategoryExists(categoryId)) {
            throw new CategoryNotFoundException();
        }
        return mapper.toResponse(handlerService.handleCategoryGet(categoryId));
    }

}
