package com.example.Car_Rental.Service;


import com.example.Car_Rental.Entity.Car;
import com.example.Car_Rental.Entity.Customer;
import com.example.Car_Rental.Entity.Rental;
import com.example.Car_Rental.Repository.CarRepository;
import com.example.Car_Rental.Repository.CustomerRepository;
import com.example.Car_Rental.Repository.RentalRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class RentalService {
    private final RentalRepository rentalRepository;
    private final CarRepository carRepository;
    private final CustomerRepository customerRepository;

    public RentalService(RentalRepository rentalRepository, CarRepository carRepository, CustomerRepository customerRepository) {
        this.rentalRepository = rentalRepository;
        this.carRepository = carRepository;
        this.customerRepository = customerRepository;
    }


    // Get all rented cars (availability = false)
    public List<Rental> getAllRentedCars() {
        return rentalRepository.findByReturnedFalse();
    }

    @Transactional
    public void bookCar(Long carId, Long customerId, int rentalDays, LocalDate startDate, LocalDate endDate,
                        String pickupBranch, String returnBranch) {
        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new RuntimeException("Car not found"));


        if (!car.isAvailability()) {
            throw new RuntimeException("Car is already rented and not available!");
        }

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        // Calculate total price
        double totalPrice = rentalDays * car.getPricePerDay();
        pickupBranch = car.getBranch();



        // Save rental
        Rental rental = new Rental(car, customer, rentalDays, false, startDate, endDate, pickupBranch, returnBranch);
        rentalRepository.save(rental);

        rental.setTotalPrice(totalPrice);

        // Mark car as unavailable
        car.setAvailability(false);
        carRepository.save(car);
    }


    @Transactional
    public void returnCar(Long rentalId) {
        Rental rental = rentalRepository.findById(rentalId)
                .orElseThrow(() -> new RuntimeException("Rental not found"));

        rental.setReturned(true);
        rentalRepository.save(rental);

        // Make car available again
        Car car = rental.getCar();
        car.setAvailability(true);
        carRepository.save(car);
    }

}

