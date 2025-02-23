package com.example.Car_Rental.Controller;

import com.example.Car_Rental.Entity.Car;
import com.example.Car_Rental.Entity.Customer;
import com.example.Car_Rental.Service.CarService;
import com.example.Car_Rental.Service.CustomerService;
import com.example.Car_Rental.Service.RentalService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/bookings")
public class BookingController {
    private final CarService carService;
    private final CustomerService customerService;
    private final RentalService rentalService;

    public BookingController(CarService carService, CustomerService customerService, RentalService rentalService) {
        this.carService = carService;
        this.customerService = customerService;
        this.rentalService = rentalService;
    }

    // Display booking page with available cars and customers

    @GetMapping
    public String showBookingPage(@RequestParam(required = false) String branch, Model model) {
        List<Car> availableCars;
        if (branch == null || branch.isEmpty()) {
            availableCars = carService.getAvailableCars(); // Fetch all available cars
        } else {
            availableCars = carService.getAvailableCarsByBranch(branch); // Fetch by branch
            model.addAttribute("branch", branch); // Keep the selected branch in the dropdown
        }

        model.addAttribute("cars", availableCars);
        model.addAttribute("customers", customerService.getAllCustomers());
        return "bookings";
    }

    // Process booking submission
    @PostMapping("/confirm")
    public String confirmBooking(@RequestParam Long carId, @RequestParam Long customerId,
                                 @RequestParam int rentalDays, @RequestParam String pickupBranch,
                                 @RequestParam String returnBranch, @RequestParam String startDate,
                                 @RequestParam String endDate) {
        rentalService.bookCar(carId, customerId, rentalDays, LocalDate.parse(startDate), LocalDate.parse(endDate),pickupBranch,returnBranch);
        return "redirect:/rentals";
    }

}
