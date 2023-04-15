package com.aditya.insurance.management.system.service;

import com.aditya.insurance.management.system.entity.Customer;
import com.aditya.insurance.management.system.entity.Policy;
import com.aditya.insurance.management.system.exceptions.CustomerNotFoundException;
import com.aditya.insurance.management.system.exceptions.PolicyNotFoundException;
import com.aditya.insurance.management.system.repository.CustomerRepository;
import com.aditya.insurance.management.system.repository.PolicyRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PolicyService {

    PolicyRepository policyRepository;
    CustomerRepository customerRepository;

    @Autowired
    public PolicyService(PolicyRepository policyRepository, CustomerRepository customerRepository) {
        this.policyRepository = policyRepository;
        this.customerRepository = customerRepository;
    }

    //Retrieve all policies
    @GetMapping("/v1/policies")
    public List<Policy> findAllCustomers() {
        return policyRepository.findAll();
    }

    //Retrieve policy by ID
    @GetMapping("/v1/policies/{id}")
    public Policy findCustomerByID(@PathVariable int id) {

        //Check if customer exists
        Optional<Policy> policy = policyRepository.findById(id);

        if(policy.isEmpty()) {
            throw new PolicyNotFoundException("Customer with id " + id + " not found");
        }

        return policy.get();
    }

    //Add new Policy
    @PostMapping("/v1/customers/{id}/policies")
    public ResponseEntity<Policy> createCustomer(@PathVariable int id, @Valid @RequestBody Policy policy) {

        //Check if customer exists
        Optional<Customer> savedCustomer = customerRepository.findById(id);

        if(savedCustomer.isEmpty()) {
            throw new CustomerNotFoundException("Customer with id " + id + " not found");
        }

        policy.setCustomer(savedCustomer.get());
        Policy savedPolicy = policyRepository.save(policy);

        return ResponseEntity.ok(savedPolicy);
    }
}
