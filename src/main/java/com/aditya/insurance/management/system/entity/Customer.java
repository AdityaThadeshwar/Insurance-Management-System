package com.aditya.insurance.management.system.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@Entity(name = "customer_details")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Size(min = 2, message = "Name should have atleast two characters")
    @Column(name = "customer_name")
    private String name;
    @Past
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;
    @Size(min = 10, max = 10, message = "Invalid mobile number")
    @Column(name = "mobile_no")
    private String mobileNo;

    public Customer(Integer id, String name, LocalDate dateOfBirth, String mobileNo) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.mobileNo = mobileNo;
    }

    public Customer() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }
}
