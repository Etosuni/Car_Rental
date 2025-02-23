package com.example.Car_Rental.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ManagementController {

    // Show management dashboard
    @GetMapping("/management")
    public String showManagementPage() {
        return "management"; // Loads management.html
    }
}
