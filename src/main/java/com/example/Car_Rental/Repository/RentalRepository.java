package com.example.Car_Rental.Repository;

import com.example.Car_Rental.Entity.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Long> {
    // Fetch only currently rented cars (availability = false)
    List<Rental> findByReturnedFalse();


    // Find the most used car based on the highest rental count
    @Query("SELECT r.car.model FROM Rental r GROUP BY r.car.model ORDER BY COUNT(r.car) DESC LIMIT 1")
    String findMostUsedCar();

    // Find the most popular pickup branch
    @Query("SELECT r.pickupBranch FROM Rental r GROUP BY r.pickupBranch ORDER BY COUNT(r.pickupBranch) DESC LIMIT 1")
    String findMostPopularPickupBranch();

    // Find the most popular return branch
    @Query("SELECT r.returnBranch FROM Rental r GROUP BY r.returnBranch ORDER BY COUNT(r.returnBranch) DESC LIMIT 1")
    String findMostPopularReturnBranch();

    // Calculate total revenue (sum of rental prices)
    @Query("SELECT SUM(r.totalPrice) FROM Rental r")
    Double calculateTotalRevenue();
    // Get revenue breakdown with rental details and customer name
    @Query("SELECT r.customer.firstName, r.customer.lastName, r.car.brand, r.car.model, " +
            "r.car.pricePerDay, r.rentalDays, (r.rentalDays * r.car.pricePerDay) as total " +
            "FROM Rental r")
    List<Object[]> getRevenueBreakdown();
}
