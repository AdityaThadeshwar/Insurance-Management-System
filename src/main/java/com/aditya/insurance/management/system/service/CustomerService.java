package com.aditya.insurance.management.system.service;

import com.aditya.insurance.management.system.entity.Customer;
import com.aditya.insurance.management.system.exceptions.CustomerNotFoundException;
import com.aditya.insurance.management.system.repository.CustomerRepository;
import jakarta.validation.Valid;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CustomerService {

    CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    //Retrieve all customers
    @GetMapping("/v1/customers")
    public List<Customer> findAllCustomers() {
        return customerRepository.findAll();
    }

    //Retrieve customer by ID
    @GetMapping("/v1/customers/{id}")
    public Customer findCustomerByID(@PathVariable int id) {

        //Check if customer exists
        Optional<Customer> customer = customerRepository.findById(id);

        if(customer.isEmpty()) {
            throw new CustomerNotFoundException("Customer with id " + id + " not found");
        }

        return customer.get();
    }

    //Add new Customer
    @PostMapping("/v1/customers")
    public ResponseEntity<Customer> createCustomer(@Valid @RequestBody Customer customer) {
        Customer savedCustomer = customerRepository.save(customer);

        return ResponseEntity.ok(savedCustomer);
    }

    //Delete customer by ID
    @DeleteMapping("/v1/customers/{id}")
    public void deleteCustomerById(@PathVariable int id) {
        customerRepository.deleteById(id);
    }

    //Update customer by ID
    @PutMapping("/v1/customers/{id}")
    public ResponseEntity<Customer> updateCustomerByID(@PathVariable int id, @RequestBody Customer customer) {

        //Check if customer exists
        Optional<Customer> updateCustomer = customerRepository.findById(id);

        if(updateCustomer.isEmpty()) {
            throw new CustomerNotFoundException("Customer with id " + id + " not found");
        }
        updateCustomer.get().setName(customer.getName());
        updateCustomer.get().setDateOfBirth(customer.getDateOfBirth());
        updateCustomer.get().setMobileNo(customer.getMobileNo());

        customerRepository.save(updateCustomer.get());

        return ResponseEntity.ok(updateCustomer.get());
    }
}
