package com.example.bgcpromogearreworked.view.operator.employee;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@Controller
@RequestMapping("/management/employee")
public class EmployeeViewController {

    @GetMapping(path = {"", "view"})
    public String showViewEmployees(Model model) {
        return "management_panel_pages/employee/employee_view";
    }

    @GetMapping("/update")
    public String showUpdateEmployee(Model model, @RequestParam UUID id,
                                     @RequestParam(defaultValue = "0") Integer pageNum,
                                     @RequestParam(defaultValue = "2") Integer pageSize,
                                     @RequestParam(defaultValue = "ID") String sortBy) {
        return "management_panel_pages/employee/employee_update";
    }
}

