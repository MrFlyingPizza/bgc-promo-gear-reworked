package ca.bgcengineering.promogearreworked.view.operator.inventory;

import ca.bgcengineering.promogearreworked.api.officelocations.officelocation.OfficeLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/management/inventory")
public class InventoryViewController {


    private final OfficeLocationService locationService;

    @Autowired
    public InventoryViewController(OfficeLocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping(path={"", "view"})
    public String showViewInventory(Model model) {
        model.addAttribute("locations", locationService.getOfficeLocations());
        return "management_panel_pages/inventory/inventory_view";
    }

}


