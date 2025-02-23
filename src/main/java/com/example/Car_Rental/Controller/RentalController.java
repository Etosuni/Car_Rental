package com.example.Car_Rental.Controller;

import com.example.Car_Rental.Entity.Rental;
import com.example.Car_Rental.Service.RentalService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/rentals")
public class RentalController {
    private final RentalService rentalService;

    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    // Display all rented cars along with customer details
    @GetMapping
    public String showRentedCars(Model model) {
        List<Rental> rentals = rentalService.getAllRentedCars();
        model.addAttribute("rentals", rentals);
        return "rentals";
    }

    // Return a rented car
    @GetMapping("/return/{id}")
    public String returnCar(@PathVariable Long id) {
        rentalService.returnCar(id);
        return "redirect:/rentals";
    }
}

