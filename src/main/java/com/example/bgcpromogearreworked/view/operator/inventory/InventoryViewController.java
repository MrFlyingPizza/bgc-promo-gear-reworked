package com.example.bgcpromogearreworked.view.operator.inventory;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/management/inventory")
public class InventoryViewController {
    @GetMapping(path={"", "view"})
    public String showViewInventory(Model model) {
        return "management_panel_pages/inventory/inventory_view";
    }

}


