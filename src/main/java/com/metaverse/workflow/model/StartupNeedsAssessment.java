package com.metaverse.workflow.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

//@Entity
//@Table(name = "startup_needs_assessment")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StartupNeedsAssessment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String startupName;

    private String state;
    private String district;
    private String city;
    private String mandal;
    private String village;

    private LocalDate dateOfEstablishment;

    private String sector;
    private String website;

    // Business Overview
    @Column(length = 2000)
    private String productDescription;

    @Column(length = 2000)
    private String problemStatement;

    @Column(length = 2000)
    private String usp;

    private String businessPlanStatus; // YES / NO / INPROGRESS

    private String businessGoalsClarity;

    private String businessModelType; // B2B, B2C, etc.

    private String startupStage;

    private Boolean dpiitRegistered;
    private String registrationType;

    // Team
    private Integer totalTeamSize;
    private Integer fullTimeEmployees;
    private Integer partTimeEmployees;

    // Market
    @Column(length = 1000)
    private String targetCustomers;

    @Column(length = 1000)
    private String marketChallenges;

    private Boolean needCustomerClarity;

    private String revenueModel;
    private Double monthlyRevenue;

    // Technology
    private String productReadinessLevel;
    private String coreTechnology;

    private String ipDetails;
    private Boolean needTechInfra;
    private String needSupportIn;
    private String digitalMaturity;

    private Boolean infraIssues;

    // Finance
    private Double totalInvestment;
    private String fundingSource;

    private String accountingStatus;//Yes No Partially

    private Boolean seekingFunding;
    private Double fundingNeed;

    // Govt Schemes
    private Boolean awareOfSchemes;
    private Boolean availedSchemes;

    // Challenges & Future
    @Column(length = 2000)
    private String challenges;

    @Column(length = 2000)
    private String futurePlans;

    @Column(length = 2000)
    private String longTermVision;

    @ElementCollection
    @CollectionTable(name = "startup_support_needs", joinColumns = @JoinColumn(name = "startup_id"))
    @Column(name = "support_need")
    private List<String> supportNeeds;

    @ElementCollection
    @CollectionTable(name = "startup_support_required", joinColumns = @JoinColumn(name = "startup_id"))
    @Column(name = "support_required")
    private List<String> supportRequired;

    // Relationships
    @OneToMany(mappedBy = "startup", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FounderDetails> founders;

    @OneToMany(mappedBy = "startup", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TeamMember> teamMembers;
}