package com.metaverse.workflow.model;

import com.metaverse.workflow.common.enums.MembershipType;
import com.metaverse.workflow.common.enums.PaymentType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "membership_application")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MembershipApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "membership_type")
    private MembershipType membershipType;

    private LocalDate applicationDate;

    private String organizationName;
    private String representativeName;

    @Column(columnDefinition = "TEXT")
    private String officeAddress;

    @Column(columnDefinition = "TEXT")
    private String residentialAddress;


    private String officePhone;
    private String residencePhone;
    private String email;

    private Double amountPaid;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_type")
    private PaymentType paymentType;

    private String chequeOrDraftNumber;

    private LocalDate paymentDate;

    private String bankName;

    // Proposal Details
    private String proposedByName;
    private String proposedBySignature;

    private String secondedByName;

    // Institution Details
    @Column(columnDefinition = "TEXT")
    private String institutionsInvolved;

    @Column(columnDefinition = "TEXT")
    private String institutionsNameAndAddress;

    @Column(columnDefinition = "TEXT")
    private String objectivesActivities;

    @Column(columnDefinition = "TEXT")
    private String natureOfInvolvement;

    // Declaration
    private Boolean agreedToRules;

    private String applicantSignature;

    // Audit Fields
    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
