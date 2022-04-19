package ca.bgcengineering.promogearreworked.view.operator.employee;

import ca.bgcengineering.promogearreworked.api.users.user.UserService;
import ca.bgcengineering.promogearreworked.api.officelocations.officelocation.OfficeLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@Controller
@RequestMapping("/management/employee")
public class EmployeeViewController {

    private final UserService service;
    private final OfficeLocationService locationService;

    @Autowired
    public EmployeeViewController(UserService service, OfficeLocationService locationService) {
        this.service = service;
        this.locationService = locationService;
    }

    @GetMapping(path = {"", "view"})
    public String showViewEmployees(Model model) {
        return "management_panel_pages/employee/employee_view";
    }

    @GetMapping("/update")
    public String showUpdateEmployee(Model model, @RequestParam UUID id,
                                     @RequestParam(defaultValue = "0") Integer pageNum,
                                     @RequestParam(defaultValue = "2") Integer pageSize,
                                     @RequestParam(defaultValue = "ID") String sortBy) {
        model.addAttribute("employee", service.getUser(id));
        model.addAttribute("locations", locationService.getOfficeLocations());
        return "management_panel_pages/employee/employee_update";
    }
}

