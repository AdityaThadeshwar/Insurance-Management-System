package com.aditya.insurance.management.system.service;

import com.aditya.insurance.management.system.entity.Address;
import com.aditya.insurance.management.system.entity.Claim;
import com.aditya.insurance.management.system.entity.Customer;
import com.aditya.insurance.management.system.entity.Policy;
import com.aditya.insurance.management.system.exceptions.ClaimNotFoundException;
import com.aditya.insurance.management.system.exceptions.CustomerNotFoundException;
import com.aditya.insurance.management.system.repository.ClaimRepository;
import com.aditya.insurance.management.system.repository.PolicyRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ClaimService {

    ClaimRepository claimRepository;
    PolicyRepository policyRepository;

    public ClaimService(ClaimRepository claimRepository, PolicyRepository policyRepository) {
        this.claimRepository = claimRepository;
        this.policyRepository = policyRepository;
    }

    @GetMapping("/v1/claims")
    public List<Claim> findAllCustomers() {
        return claimRepository.findAll();
    }

    //Retrieve Claim by ID
    @GetMapping("/v1/claims/{id}")
    public Claim findClaimByID(@PathVariable int id) {

        //Check if customer exists
        Optional<Claim> claim = claimRepository.findById(id);

        if(claim.isEmpty()) {
            throw new ClaimNotFoundException("Claim with id " + id + " not found");
        }

        return claim.get();
    }

    //Add new Claim
    @PostMapping("/v1/policies/{id}/claims")
    public ResponseEntity<Claim> createClaim(@PathVariable int id, @Valid @RequestBody Claim claim) {

        //Check if customer exists
        Optional<Policy> savedPolicy = policyRepository.findById(id);

        if(savedPolicy.isEmpty()) {
            throw new CustomerNotFoundException("Policy with id " + id + " not found");
        }

        claim.setPolicy(savedPolicy.get());
        Claim savedClaim = claimRepository.save(claim);

        return ResponseEntity.ok(savedClaim);
    }

    //Delete Claim by ID
    @DeleteMapping("/v1/policies/{id}")
    public void deletePolicyById(@PathVariable int id) {
        claimRepository.deleteById(id);
    }

    //Update Policy by ID
    @PutMapping("/v1/policies/{id}")
    public ResponseEntity<Policy> updatePolicyByID(@PathVariable int id, @RequestBody Policy policy) {

        //Check if customer exists
        Optional<Policy> updatePolicy = policyRepository.findById(id);

        if(updatePolicy.isEmpty()) {
            throw new CustomerNotFoundException("Policy with id " + id + " not found");
        }

        //Update policy attributes
        updatePolicy.get().setPolicyNo(policy.getPolicyNo());
        updatePolicy.get().setCoverageAmount(policy.getCoverageAmount());
        updatePolicy.get().setPremium(policy.getPremium());
        updatePolicy.get().setEffectiveDate(policy.getEffectiveDate());
        updatePolicy.get().setExpiryDate(policy.getExpiryDate());
        //updatePolicy.get().setCustomer(policy.getCustomer());

        Customer customer = updatePolicy.get().getCustomer();
        Address address = customer.getAddress();


        policyRepository.save(updatePolicy.get());

        return ResponseEntity.ok(updatePolicy.get());
    }
}
