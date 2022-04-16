package com.example.bgcpromogearreworked.view.customer;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/store")
public class StoreViewController {

    @GetMapping(path={"","/products"})
    public String showMarketplace(Model model) {
        return "store_pages/marketplace";
    }

    @GetMapping("/product")
    public String showProductPage(Model model, @RequestParam int id){
        return "store_pages/product_listing";
    }

    @GetMapping("cart")
    public String showCartPage(){
        return "store_pages/cart";
    }

    @GetMapping("checkout")
    public String showCheckoutOrderPage(){
        return "store_pages/checkout";
    }



}
