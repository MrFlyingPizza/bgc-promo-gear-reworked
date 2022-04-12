package ca.bgcengineering.promogearreworked.view.operator.transfer;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/management/transfer")
public class TransferViewController {

    @GetMapping(path={"", "view"})
    public String showViewTransfers(){
        return "management_panel_pages/transfer/transfer_view";
    }
}


