package com.aditya.insurance.management.system.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

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
    private Date endDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id", referencedColumnName = "id" )
    private Customer customer;
}
