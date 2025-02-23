package com.example.Car_Rental.Entity;

import jakarta.persistence.*;


@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;

    @Enumerated(EnumType.STRING)
    private Position position;

    private String branch;

    public Employee() {}

    public Employee(String firstName, String lastName, Position position, String branch) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.branch = branch;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public Position getPosition() { return position; }
    public void setPosition(Position position) { this.position = position; }

    public String getBranch() { return branch; }
    public void setBranch(String branch) { this.branch = branch; }
}

