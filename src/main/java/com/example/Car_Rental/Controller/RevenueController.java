package com.example.Car_Rental.Controller;

import com.example.Car_Rental.Service.RevenueService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RevenueController {

    private final RevenueService revenueService;

    public RevenueController(RevenueService revenueService) {
        this.revenueService = revenueService;
    }

    @GetMapping("/revenue")
    public String showRevenuePage(Model model) {
        model.addAttribute("totalRentals", revenueService.getTotalRentals());
        model.addAttribute("mostUsedCar", revenueService.getMostUsedCar());
        model.addAttribute("mostPopularPickupBranch", revenueService.getMostPopularPickupBranch());
        model.addAttribute("mostPopularReturnBranch", revenueService.getMostPopularReturnBranch());
        model.addAttribute("totalRevenue", revenueService.getTotalRevenue()); // Total money collected
        model.addAttribute("revenueBreakdown", revenueService.getRevenueBreakdown());

        return "revenue";
    }
}
