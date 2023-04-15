package com.aditya.insurance.management.system.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Entity(name = "customer_details")
@Getter
@Setter
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

    @OneToOne
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @OneToMany(mappedBy = "customer")
    @JsonIgnore
    private Set<Policy> policy;

}
