package com.metaverse.workflow.membership.service;

import com.metaverse.workflow.common.enums.MembershipType;
import com.metaverse.workflow.common.enums.PaymentType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
public class MembershipApplicationResponse {

    private Long id;
    private String name;
    private MembershipType membershipType;
    private LocalDate applicationDate;

    private String organizationName;
    private String representativeName;

    private AddressResponse officeAddress;
    private AddressResponse residentialAddress;

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
    private String transactionId;
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

    // Instead of returning the whole User object
    private String createdBy;

    private String idProofPath;
    private String photoPath;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    @Data
    @Builder
    public static class AddressResponse {

        private Long id;
        private String houseNo;
        private String streetName;
        private String landmark;
        private String location;
        private String village;
        private String villageOther;
        private String mandal;
        private String mandalOther;
        private String district;
        private String state;
        private String pincode;
    }
}