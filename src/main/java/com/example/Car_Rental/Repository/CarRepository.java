package com.example.Car_Rental.Repository;

import com.example.Car_Rental.Entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findByAvailabilityTrue();
    List<Car> findByAvailabilityTrueAndBranch(String branch);
}
