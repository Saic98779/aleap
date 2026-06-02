package com.metaverse.workflow.startupSurvey.model;

import com.metaverse.workflow.startupSurvey.Enums.*;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "startup_survey")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StartupSurvey extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String startupName;

    @Column(name = "founder_name")
    private String founderName;

    @Enumerated(EnumType.STRING)
    private Gender genderOfPrimaryFounder;

    private Integer ageOfPrimaryFounder;

    private String educationalQualification;

    private String email;

    private String phone;

    private String city;

    private String district;

    private String state;

    private LocalDate dateOfEstablishment;

    private String sectorIndustry;

    @Column(name = "web_site")
    private String webSite;

    private String productServiceDescription;

    private String problemSolving;

    private String uniqueValueProposition;

    @Enumerated(EnumType.STRING)
    private BusinessPlanStatus businessPlanStatus;

    @Enumerated(EnumType.STRING)
    private BusinessGoalsClarity businessGoalsClarity;

    @ElementCollection(targetClass = BusinessMentoring.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(
            name = "startup_business_mentoring",
            joinColumns = @JoinColumn(name = "startup_survey_id")
    )
    @Column(name = "mentoring_type")
    private List<BusinessMentoring> businessMentoringTypes;

    @Enumerated(EnumType.STRING)
    private StartupStage startupStage;

    @Enumerated(EnumType.STRING)
    private BusinessModelType businessModelType;

    private Boolean dpiitRegistered;

    @Enumerated(EnumType.STRING)
    private RegistrationType registrationType;

    private Integer totalTeamSize;

    private Integer fullTimeEmployees;

    private Integer partTimeEmployees;

    @OneToMany(mappedBy = "startupSurvey", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<KeyTeamMember> keyTeamMembers;

    @ElementCollection(targetClass = SupportNeeded.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(
            name = "startup_support_needed",
            joinColumns = @JoinColumn(name = "startup_survey_id")
    )
    @Column(name = "support_type")
    private List<SupportNeeded> supportNeeded;


    // IV. Market & Customers

    @Column(columnDefinition = "TEXT")
    private String targetCustomerSegment;

    private Boolean customerSegmentClarityNeeded;

    @ElementCollection(targetClass = PrimaryChallenge.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(
            name = "startup_primary_challenges",
            joinColumns = @JoinColumn(name = "startup_survey_id")
    )
    @Column(name = "challenge")
    private List<PrimaryChallenge> primaryChallenges;

    @ElementCollection(targetClass = SupportRequired.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(
            name = "startup_support_required",
            joinColumns = @JoinColumn(name = "startup_survey_id")
    )
    @Column(name = "support_type")
    private List<SupportRequired> supportRequired;

    @Enumerated(EnumType.STRING)
    private RevenueModel revenueModel;

    private BigDecimal monthlyRevenue;

    // V. Technology & Innovation

    @Enumerated(EnumType.STRING)
    private ProductReadinessLevel productReadinessLevel;

    @ElementCollection(targetClass = ProductDevelopmentSupport.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(
            name = "startup_product_development_support",
            joinColumns = @JoinColumn(name = "startup_survey_id")
    )
    @Column(name = "support_type")
    private List<ProductDevelopmentSupport> productDevelopmentSupports;

    @Column(columnDefinition = "TEXT")
    private String coreTechnologyUsed;

    @Column(columnDefinition = "TEXT")
    private String ipsFiledOrPlanned;

    private Boolean technicalInfrastructureNeeded;

    @Column(columnDefinition = "TEXT")
    private String technicalInfrastructureDescription;

    @ElementCollection(targetClass = DigitalMaturity.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(
            name = "startup_digital_maturity",
            joinColumns = @JoinColumn(name = "startup_survey_id")
    )
    @Column(name = "tool")
    private List<DigitalMaturity> digitalMaturityTools;

    @ElementCollection(targetClass = TechnicalInfrastructureSupport.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(
            name = "startup_technical_infrastructure_support",
            joinColumns = @JoinColumn(name = "startup_survey_id")
    )
    @Column(name = "support_type")
    private List<TechnicalInfrastructureSupport> technicalInfrastructureSupports;

    private Boolean infraRelatedIssues;

    @ElementCollection(targetClass = InfrastructureAssistance.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(
            name = "startup_infrastructure_assistance",
            joinColumns = @JoinColumn(name = "startup_survey_id")
    )
    @Column(name = "assistance_type")
    private List<InfrastructureAssistance> infrastructureAssistance;
}
