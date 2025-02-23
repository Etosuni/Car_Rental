package com.example.Car_Rental.Entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "car_id", nullable = false)
    private Car car;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Column(nullable = false)
    private boolean returned = false;

    private int rentalDays;

    @Column(nullable = false)
    private String pickupBranch;

    @Column(nullable = false)
    private String returnBranch;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    @Column(nullable = true) // Allows null initially
    private Double totalPrice;

    public Rental() {}

    public Rental(Car car, Customer customer, int rentalDays, boolean returned, LocalDate startDate, LocalDate endDate,
                  String pickupBranch, String returnBranch) {
        this.car = car;
        this.customer = customer;
        this.rentalDays = rentalDays;
        this.returned = returned;
        this.startDate = startDate;
        this.endDate = endDate;
        this.pickupBranch = pickupBranch;
        this.returnBranch = returnBranch;
        this.totalPrice = calculateTotalPrice(); // Automatically calculate price
    }

    public double calculateTotalPrice() {
        return rentalDays * car.getPricePerDay();
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public boolean isReturned() {
        return returned;
    }

    public void setReturned(boolean returned) {
        this.returned = returned;
    }

    public int getRentalDays() {
        return rentalDays;
    }

    public void setRentalDays(int rentalDays) {
        this.rentalDays = rentalDays;
    }

    public String getPickupBranch() {
        return pickupBranch;
    }

    public void setPickupBranch(String pickupBranch) {
        this.pickupBranch = pickupBranch;
    }

    public String getReturnBranch() {
        return returnBranch;
    }

    public void setReturnBranch(String returnBranch) {
        this.returnBranch = returnBranch;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Double getTotalPrice() { return totalPrice; }
    public void setTotalPrice(Double totalPrice) { this.totalPrice = totalPrice; }
}

