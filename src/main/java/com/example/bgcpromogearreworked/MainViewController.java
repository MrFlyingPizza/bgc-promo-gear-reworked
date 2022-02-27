package com.example.bgcpromogearreworked;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainViewController {

    @RequestMapping
    private String main() {
        return "main";
    }
}
