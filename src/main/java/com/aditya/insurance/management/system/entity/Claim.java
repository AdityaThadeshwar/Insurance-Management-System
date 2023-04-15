package com.aditya.insurance.management.system.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity(name = "claim_details")
@Getter
@Setter
public class Claim {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "claim_no")
    private String claimNo;

    @Column(name = "loss_description")
    private String lossDescription;

    @Column(name = "date_of_loss")
    private Date dateOfLoss;

    @Column(name = "claim_status")
    private String claimStatus;

    @Column(name = "policy_id")
    private Integer policyId;

    @ManyToOne
    @JoinColumn(name = "policy_id", referencedColumnName = "id", insertable=false, updatable=false)
    private Policy policy;
}
