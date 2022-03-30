package com.example.bgcpromogearreworked.view.operator.location;

import com.example.bgcpromogearreworked.api.officelocations.officelocation.OfficeLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/management/location")
public class LocationViewController {


    private final OfficeLocationService service;

    @Autowired
    public LocationViewController(OfficeLocationService service) {
        this.service = service;
    }

    /**
     * Handle request to get all locations and attaches to model for location viewing.
     * @param model model
     * @return html page for viewing locations
     */
    @GetMapping(path = {"", "view"})
    public String showViewLocations(Model model) {
        return "management_panel_pages/location/location_view";
    }

    @GetMapping("/create")
    public String showCreateLocation() {
        return "management_panel_pages/location/location_create";
    }

    @GetMapping("/update")
    public String showUpdateLocation(Model model, @RequestParam long id) {
        model.addAttribute("location", service.getOfficeLocation(id));
        return "management_panel_pages/location/location_update";
    }
}
