package com.example.Car_Rental.Entity;

import jakarta.persistence.*;

@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String brand;
    private String model;
    private String serialNumber;
    private int year;
    private String color;
    private String fuelType;
    private String transmissionType;
    private int mileage;
    private String category;
    private double pricePerDay; // Price stored in the car table

    @Column(nullable = false)
    private boolean availability = true; // Default to available
    private String branch;  // Stores the branch location
    public Car() {}

    public Car(String brand, String model, String serialNumber, int year, String color, String fuelType, String transmissionType, int mileage, String category, double pricePerDay,String branch) {
        this.brand = brand;
        this.model = model;
        this.serialNumber = serialNumber;
        this.year = year;
        this.color = color;
        this.fuelType = fuelType;
        this.transmissionType = transmissionType;
        this.mileage = mileage;
        this.category = category;
        this.pricePerDay = pricePerDay;
        this.availability = true;
        this.branch = branch;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getTransmissionType() {
        return transmissionType;
    }

    public void setTransmissionType(String transmissionType) {
        this.transmissionType = transmissionType;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public String getBranch() { return branch; }
    public void setBranch(String branch) { this.branch = branch; }
}
