package com.example.Car_Rental.Service;

import com.example.Car_Rental.Repository.RentalRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RevenueService {

    private final RentalRepository rentalRepository;

    public RevenueService(RentalRepository rentalRepository) {
        this.rentalRepository = rentalRepository;
    }

    // Get total number of rentals
    public long getTotalRentals() {
        return rentalRepository.count();
    }

    // Get the most rented car model
    public String getMostUsedCar() {
        return rentalRepository.findMostUsedCar();
    }

    // Get the most popular pickup branch
    public String getMostPopularPickupBranch() {
        return rentalRepository.findMostPopularPickupBranch();
    }

    // Get the most popular return branch
    public String getMostPopularReturnBranch() {
        return rentalRepository.findMostPopularReturnBranch();
    }

    // Get total revenue (sum of all rental prices)
    public double getTotalRevenue() {
        return rentalRepository.calculateTotalRevenue();
    }
    // Get revenue breakdown (list of rentals with price calculation details)
    public List<Object[]> getRevenueBreakdown() {
        return rentalRepository.getRevenueBreakdown();
    }
}
