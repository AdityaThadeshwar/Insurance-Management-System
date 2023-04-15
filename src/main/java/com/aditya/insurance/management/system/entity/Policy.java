package com.aditya.insurance.management.system.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Entity(name = "policy_details")
@Getter
@Setter
public class Policy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "policy_no")
    private String policyNo;

    @Column(name = "coverage_amount")
    private Long coverageAmount;

    @Column(name = "premium")
    private Long premium;

    @Column(name = "effective_date")
    private Date effectiveDate;

    @Column(name = "expiry_date")
    private Date expiryDate;

    @Column(name = "customer_id")
    private Integer customerId;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id", insertable=false, updatable=false)
    private Customer customer;

    @OneToMany(mappedBy = "policy")
    @JsonIgnore
    private Set<Claim> claim;
}
