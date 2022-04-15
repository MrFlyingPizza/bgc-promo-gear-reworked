package ca.bgcengineering.promogearreworked.view.operator.product;

import ca.bgcengineering.promogearreworked.api.categories.category.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/management/product")
public class ProductViewController {

    private final CategoryService categoryService;

    public ProductViewController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping(path = {"", "view"})
    public String showViewProducts(Model model) {
        return "management_panel_pages/product/product_view";
    }

    @GetMapping("/create")
    public String showCreateProduct(Model model){
        model.addAttribute("categories", categoryService.getCategories());
        return "management_panel_pages/product/product_create";
    }

    @GetMapping("/update")
    public String showUpdateProduct(Model model, @RequestParam int id){
        return "management_panel_pages/product/product_update";
    }



}


