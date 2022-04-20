package ca.bgcengineering.promogearreworked.view.operator.product;

import ca.bgcengineering.promogearreworked.api.categories.category.CategoryService;
import ca.bgcengineering.promogearreworked.api.options.option.OptionService;
import ca.bgcengineering.promogearreworked.api.products.image.ProductImageService;
import ca.bgcengineering.promogearreworked.api.products.product.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/management/product")
public class ProductViewController {

    private final CategoryService categoryService;
    private final OptionService optionService;
    private final ProductService productService;
    private final ProductImageService productImageService;

    public ProductViewController(CategoryService categoryService, OptionService optionService,
                                 ProductService productService, ProductImageService productImageService) {
        this.categoryService = categoryService;
        this.optionService = optionService;
        this.productService = productService;
        this.productImageService = productImageService;
    }

    @GetMapping(path = {"", "view"})
    public String showViewProducts(Model model) {
        return "management_panel_pages/product/product_view";
    }

    @GetMapping("/create")
    public String showCreateProduct(Model model){
        model.addAttribute("categories", categoryService.getCategories());
        model.addAttribute("options", optionService.getOptions());
        return "management_panel_pages/product/product_create";
    }

    @GetMapping("/update")
    public String showUpdateProduct(Model model, @RequestParam int id){
        model.addAttribute("product", productService.getProduct((long) id));
        model.addAttribute("images", productImageService.getProductImages((long) id));
        return "management_panel_pages/product/product_update";
    }




}


