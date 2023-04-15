package com.aditya.insurance.management.system.service;

import com.aditya.insurance.management.system.entity.Customer;
import com.aditya.insurance.management.system.entity.Policy;
import com.aditya.insurance.management.system.exceptions.CustomerNotFoundException;
import com.aditya.insurance.management.system.exceptions.PolicyNotFoundException;
import com.aditya.insurance.management.system.repository.CustomerRepository;
import com.aditya.insurance.management.system.repository.PolicyRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public List<Policy> findAllPolicies() {
        return policyRepository.findAll();
    }

    //Retrieve policy by ID
    @GetMapping("/v1/policies/{id}")
    public Policy findPolicyByID(@PathVariable int id) {

        //Check if customer exists
        Optional<Policy> policy = policyRepository.findById(id);

        if(policy.isEmpty()) {
            throw new PolicyNotFoundException("Policy with id " + id + " not found");
        }

        return policy.get();
    }

    //Add new Policy
    @PostMapping("/v1/customers/{id}/policies")
    public ResponseEntity<Policy> createPolicy(@PathVariable int id, @Valid @RequestBody Policy policy) {

        //Check if customer exists
        Optional<Customer> savedCustomer = customerRepository.findById(id);

        if(savedCustomer.isEmpty()) {
            throw new CustomerNotFoundException("Policy with id " + id + " not found");
        }

        //Update customer id from request in the policy and persist
        policy.setCustomerId(id);
        Policy savedPolicy = policyRepository.save(policy);

        savedPolicy.setCustomer(savedCustomer.get());
        return ResponseEntity.status((HttpStatus.CREATED)).body(savedPolicy);
    }

    //Delete Policy by ID
    @DeleteMapping("/v1/policies/{id}")
    public void deletePolicyById(@PathVariable int id) {
        policyRepository.deleteById(id);
    }

    //Update Policy by ID
    @PutMapping("/v1/policies/{id}")
    public ResponseEntity<Policy> updatePolicyByID(@PathVariable int id, @RequestBody Policy policy) {

        //Check if customer exists
        Optional<Policy> updatePolicy = policyRepository.findById(id);

        if(updatePolicy.isEmpty()) {
            throw new CustomerNotFoundException("Policy with id " + id + " not found");
        }

        //Update retrieved policy with new details from request
        updatePolicy.get().setPolicyNo(policy.getPolicyNo());
        updatePolicy.get().setCoverageAmount(policy.getCoverageAmount());
        updatePolicy.get().setPremium(policy.getPremium());
        updatePolicy.get().setEffectiveDate(policy.getEffectiveDate());
        updatePolicy.get().setExpiryDate(policy.getExpiryDate());

        policyRepository.save(updatePolicy.get());

        return ResponseEntity.ok(updatePolicy.get());
    }
}
