package com.aditya.insurance.management.system.service;

import com.aditya.insurance.management.system.entity.Claim;
import com.aditya.insurance.management.system.entity.Policy;
import com.aditya.insurance.management.system.exceptions.ClaimNotFoundException;
import com.aditya.insurance.management.system.exceptions.CustomerNotFoundException;
import com.aditya.insurance.management.system.repository.ClaimRepository;
import com.aditya.insurance.management.system.repository.PolicyRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
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

        //Update policy id from request in the claim and persist
        claim.setPolicyId(id);
        Claim savedClaim = claimRepository.save(claim);

        savedClaim.setPolicy(savedPolicy.get());
        return ResponseEntity.status((HttpStatus.CREATED)).body(savedClaim);
    }

    //Delete Claim by ID
    @DeleteMapping("/v1/claims/{id}")
    public void deletePolicyById(@PathVariable int id) {
        claimRepository.deleteById(id);
    }

    //Update Claim by ID
    @PutMapping("/v1/claims/{id}")
    public ResponseEntity<Claim> updatePolicyByID(@PathVariable int id, @RequestBody Claim claim) {

        //Check if customer exists
        Optional<Claim> updateClaim = claimRepository.findById(id);

        if(updateClaim.isEmpty()) {
            throw new ClaimNotFoundException("Policy with id " + id + " not found");
        }

        //Update claim attributes from request
        updateClaim.get().setClaimNo(claim.getClaimNo());
        updateClaim.get().setClaimStatus(claim.getClaimStatus());
        updateClaim.get().setDateOfLoss(claim.getDateOfLoss());
        updateClaim.get().setLossDescription(claim.getLossDescription());

        claimRepository.save(updateClaim.get());

        return ResponseEntity.ok(updateClaim.get());
    }


}
