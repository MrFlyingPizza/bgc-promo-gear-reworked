package ca.bgcengineering.promogearreworked.view.operator.product;

import ca.bgcengineering.promogearreworked.api.products.variant.ProductVariantService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/management/product_variant")
public class ProductVariantViewController {

//    @GetMapping(path = {"", "view"})
//    public String showViewProducts(Model model) {
//        return ;
//    }

    private final ProductVariantService productVariantService;

    public ProductVariantViewController(ProductVariantService productVariantService) {
        this.productVariantService = productVariantService;
    }

    @GetMapping("/update")
    public String showUpdateProductVariant(Model model, @RequestParam int id){
        model.addAttribute("variant", productVariantService.getProductVariant((long) id));

        return "management_panel_pages/product/product_variant_update";
    }
}
