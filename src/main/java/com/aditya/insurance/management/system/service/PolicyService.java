package com.aditya.insurance.management.system.service;

import com.aditya.insurance.management.system.entity.Customer;
import com.aditya.insurance.management.system.entity.Policy;
import com.aditya.insurance.management.system.repository.PolicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PolicyService {

    PolicyRepository policyRepository;

    @Autowired
    public PolicyService(PolicyRepository policyRepository) {
        this.policyRepository = policyRepository;
    }

    //Retrieve all policies
    @GetMapping("/v1/policies")
    public List<Policy> findAllCustomers() {
        return policyRepository.findAll();
    }
}
