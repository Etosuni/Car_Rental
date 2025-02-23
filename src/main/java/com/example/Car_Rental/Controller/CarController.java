package com.example.Car_Rental.Controller;

import com.example.Car_Rental.Entity.Car;
import com.example.Car_Rental.Service.CarService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cars")
public class CarController {
    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    public String listCars(Model model) {
        model.addAttribute("cars", carService.getAllCars());
        return "cars";
    }

    @GetMapping("/add-car")
    public String showAddCarPage(Model model) {
        model.addAttribute("newCar", new Car());
        return "add-car";
    }

    @PostMapping("/add")
    public String addCar(@ModelAttribute Car car) {
        carService.addCar(car);
        return "redirect:/cars";
    }

    @GetMapping("/delete/{id}")
    public String deleteCar(@PathVariable Long id) {
        carService.deleteCar(id);
        return "redirect:/cars";
    }
    @GetMapping("/edit/{id}")
    public String editCar(@PathVariable Long id, Model model) {
        Car car = carService.getCarById(id);
        model.addAttribute("car", car);
        return "edit-car";
    }

    @PostMapping("/update")
    public String updateCar(@ModelAttribute Car car) {
        carService.updateCar(car);
        return "redirect:/cars";
    }
}


