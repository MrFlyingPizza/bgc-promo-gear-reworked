package ca.bgcengineering.promogearreworked.view.customer;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/store")
public class StoreViewController {

    @GetMapping(path={"","/products"})
    public String showMarketplace(Model model) {
        return "store_pages/store";
    }

    /*
    @GetMapping("/product")
    public String showProductPage(Model model, @RequestParam int id){
        return "store_pages/product_listing";
    }

    @GetMapping("cart")
    public String showCartPage(){
        return "store_pages/new_cart";
    }

    @GetMapping("checkout")
    public String showCheckoutOrderPage(){
        return "store_pages/checkout";
    }
    */

    @GetMapping("/product/{productId}")
    public String showProductPage(Model model, @PathVariable Long productId){
        model.addAttribute("productId", productId);
        return "store_pages/product";
    }

    @GetMapping("checkout")
    public String showCartPage(){
        return "/store_pages/checkout";
    }

    @GetMapping("orders")
    public String showMyOrdersPage(){
        return "/store_pages/orders";
    }

}
