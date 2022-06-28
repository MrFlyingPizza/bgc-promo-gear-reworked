package ca.bgcengineering.promogearreworked.view.operator.orders;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/management/orders")
public class OrdersViewController {

    /*
    @GetMapping(path={"", "view"})
    public String showViewTransfers(){
        return "management_panel_pages/transfer/transfer_view";
    }
     */

    @GetMapping(path={"", "view"})
    public String showViewTransfers(){
        return "management_panel_pages/orders/orders";
    }
}