package ca.bgcengineering.promogearreworked.view.operator.product;

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

    @GetMapping("/update")
    public String showUpdateProductVariant(Model model, @RequestParam int id){
        return "management_panel_pages/product/product_variant_update";
    }
}
