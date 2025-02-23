package com.example.Car_Rental.Controller;

import com.example.Car_Rental.Entity.Employee;
import com.example.Car_Rental.Repository.EmployeeRepository;
import com.example.Car_Rental.Entity.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    // Show all employees
    @GetMapping
    public String showEmployees(Model model) {
        List<Employee> employees = employeeRepository.findAll();
        model.addAttribute("employees", employees);
        return "employees"; // Renders employees.html
    }

    // Show employee details
    @GetMapping("/{id}")
    public String showEmployeeDetails(@PathVariable Long id, Model model) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            model.addAttribute("employee", employee.get());
            return "employee-details"; // Renders employee-details.html
        } else {
            return "redirect:/employees";
        }
    }

    // Show the add employee form
    @GetMapping("/add")
    public String showAddEmployeeForm(Model model) {
        model.addAttribute("employee", new Employee());
        model.addAttribute("positions", Position.values());
        return "add-employee"; // Renders add-employee.html
    }

    // Handle employee submission
    @PostMapping("/add")
    public String addEmployee(@ModelAttribute Employee employee) {
        employeeRepository.save(employee);
        return "redirect:/employees";
    }
    // Show the edit employee form
    @GetMapping("/edit/{id}")
    public String editEmployeeForm(@PathVariable Long id, Model model) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            model.addAttribute("employee", employee.get());
            model.addAttribute("positions", Position.values());
            return "edit-employee";
        }
        return "redirect:/employees"; // Redirect if not found
    }

    // Handle editing employee
    @PostMapping("/edit/{id}")
    public String editEmployee(@PathVariable Long id, @ModelAttribute Employee updatedEmployee) {
        Optional<Employee> existingEmployee = employeeRepository.findById(id);
        if (existingEmployee.isPresent()) {
            Employee employee = existingEmployee.get();
            employee.setFirstName(updatedEmployee.getFirstName());
            employee.setLastName(updatedEmployee.getLastName());
            employee.setPosition(updatedEmployee.getPosition());
            employee.setBranch(updatedEmployee.getBranch());
            employeeRepository.save(employee);
        }
        return "redirect:/employees";
    }

    // Handle deleting employee
    @PostMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        employeeRepository.deleteById(id);
        return "redirect:/employees";
    }
}
