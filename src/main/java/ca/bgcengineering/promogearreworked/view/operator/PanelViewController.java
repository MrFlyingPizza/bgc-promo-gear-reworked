package ca.bgcengineering.promogearreworked.view.operator;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/management/panel")
public class PanelViewController {

    @GetMapping(path={"","/dashboard"})
    public String showFrontPage() {
        return "management_panel_pages/dashboard";
    }

}
