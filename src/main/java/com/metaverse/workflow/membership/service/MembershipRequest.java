package com.metaverse.workflow.membership.service;

import com.metaverse.workflow.common.enums.MembershipType;
import com.metaverse.workflow.common.enums.PaymentType;
import com.metaverse.workflow.model.Address;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MembershipRequest {

    private String name;
    private MembershipType membershipType;
    private LocalDate applicationDate;

    private String organizationName;
    private String representativeName;

    private Address officeAddress;
    private Address residentialAddress;

    private String officePhone;
    private String residencePhone;
    private String email;

    private Double amount;
    private String billNo;
    private Date billDate;
    private String payeeName;
    private PaymentType paymentType;
    private String bankName;
    private String ifscCode;
    private String transactionId;//for upi
    private String checkNo;
    private LocalDate checkDate;
    private String purpose;
    private String billPath;

    private String proposedByName;
    private String signaturePath;
    private String secondedByName;

    private String institutionsInvolved;
    private String institutionsNameAndAddress;
    private String objectivesActivities;
    private String natureOfInvolvement;

    private Boolean agreedToRules;

    private String userId;

    private String idProofPath;
    private String photoPath;
}
