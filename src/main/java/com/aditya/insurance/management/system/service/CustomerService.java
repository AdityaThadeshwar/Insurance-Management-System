package com.aditya.insurance.management.system.service;

import com.aditya.insurance.management.system.entity.Customer;
import com.aditya.insurance.management.system.exceptions.CustomerNotFoundException;
import com.aditya.insurance.management.system.repository.CustomerRepository;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class CustomerService {

    CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    //Return all customers
    @GetMapping("/v1/customers")
    public List<Customer> findAllCustomers() {
        return customerRepository.findAll();
    }

    //Return customer by ID
    @GetMapping("/v1/customers/{id}")
    public Customer findCustomerByID(@PathVariable int id) {

        Optional<Customer> customer = customerRepository.findById(id);

        if(customer.isEmpty()) {
            throw new CustomerNotFoundException("Customer with id " + id + " not found");
        }

        return customer.get();
    }
}
