package com.aditya.insurance.management.system.service;

import com.aditya.insurance.management.system.entity.Address;
import com.aditya.insurance.management.system.entity.Customer;
import com.aditya.insurance.management.system.exceptions.AddressNotFoundException;
import com.aditya.insurance.management.system.exceptions.CustomerNotFoundException;
import com.aditya.insurance.management.system.repository.AddressRepository;
import com.aditya.insurance.management.system.repository.CustomerRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CustomerService {

    CustomerRepository customerRepository;
    AddressRepository addressRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, AddressRepository addressRepository) {
        this.customerRepository = customerRepository;
        this.addressRepository = addressRepository;
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

        //Check if customer exists
        if(customer.getAddress() == null) {
            throw new AddressNotFoundException("Address is not present for the customer");
        }

        //Persist address from request
        Address customerAddress = customer.getAddress();
        Address savedAddress = addressRepository.save(customerAddress);

        //Map id from saved address to customer and persist customer
        customer.setAddressId(savedAddress.getId());
        Customer savedCustomer = customerRepository.save(customer);

        return ResponseEntity.status((HttpStatus.CREATED)).body(savedCustomer);
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

        //Update retrieved customer with new details from request
        updateCustomer.get().setName(customer.getName());
        updateCustomer.get().setDateOfBirth(customer.getDateOfBirth());
        updateCustomer.get().setMobileNo(customer.getMobileNo());

        customerRepository.save(updateCustomer.get());

        return ResponseEntity.ok(updateCustomer.get());
    }
}
