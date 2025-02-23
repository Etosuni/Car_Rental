package com.example.Car_Rental.Controller;


import com.example.Car_Rental.Entity.Customer;
import com.example.Car_Rental.Service.CustomerService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public String listCustomers(Model model) {
        List<Customer> customers = customerService.getAllCustomers();
        model.addAttribute("customers", customers);
        return "customers";
    }
    // Save or Update Customer
    @PostMapping("/update")
    public String saveCustomer(@ModelAttribute Customer customer) {
        customerService.saveCustomer(customer);
        return "redirect:/customers";

    }  // Show Edit Customer Page
    @GetMapping("/edit/{id}")
    public String showEditCustomerPage(@PathVariable Long id, Model model) {
        Customer customer = customerService.getCustomerById(id);
        model.addAttribute("customer", customer);
        return "edit-customer";
    }


    @GetMapping("/add-customer")
    public String showAddCustomerPage(Model model) {
        model.addAttribute("newCustomer", new Customer());
        return "add-customer";
    }

    @PostMapping("/add")
    public String addCustomer(@ModelAttribute Customer customer, Model model) {
        try {
            customerService.saveCustomer(customer);
            return "redirect:/customers";
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("error1", "A customer with this Personal Number already exists.");
            return "add-customer";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return "redirect:/customers";
    }
}
