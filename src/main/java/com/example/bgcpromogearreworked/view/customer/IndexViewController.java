package com.example.bgcpromogearreworked.view.customer;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexViewController {

    @GetMapping(path={"","/main"})
    public String showFrontPage() {
        return "front_pages/main";
    }

    @GetMapping("/faq")
    public String showFAQ() {
        return "front_pages/faq";
    }

    @GetMapping("/guidelines")
    public String showGuidelines() {
        return "front_pages/guidelines";
    }

    @GetMapping("/product_care")
    public String showProductCareInfo() {
        return "front_pages/product_care";
    }

    @GetMapping("/warranty")
    public String showWarrantyInfo() {
        return "front_pages/warranty";
    }

}
