package com.example.Car_Rental.Service;


import com.example.Car_Rental.Entity.Car;
import com.example.Car_Rental.Repository.CarRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CarService {
    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }


    public void deleteCar(Long carid) {
        carRepository.deleteById(carid);
    }

    // Update car availability after booking
    public void updateCarAvailability(Long carId, boolean status) {
        Car car = carRepository.findById(carId).orElseThrow(() -> new RuntimeException("Car not found"));
        car.setAvailability(status);
        carRepository.save(car);
    }
    // Fetch all available cars
    public List<Car> getAvailableCars() {
        return carRepository.findByAvailabilityTrue();
    }

    // Fetch available cars filtered by branch
    public List<Car> getAvailableCarsByBranch(String branch) {
        return carRepository.findByAvailabilityTrueAndBranch(branch);
    }
    // Add a new car (Always Available by Default)
    public void addCar(Car car) {
        car.setAvailability(true); // Force availability to true
        carRepository.save(car);
    }
    public Car getCarById(Long id) {
        return carRepository.findById(id).orElseThrow(() -> new RuntimeException("Car not found"));
    }

    public void updateCar(Car car) {
        carRepository.save(car); // Save updates
    }



}

