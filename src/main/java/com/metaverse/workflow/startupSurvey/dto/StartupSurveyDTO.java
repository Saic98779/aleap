package com.metaverse.workflow.startupSurvey.dto;

import com.metaverse.workflow.startupSurvey.Enums.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StartupSurveyDTO {

    private Long id;

    private String startupName;

    private String founderName;

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

    private String webSite;

    private String productServiceDescription;

    private String problemSolving;

    private String uniqueValueProposition;

    private BusinessPlanStatus businessPlanStatus;

    private BusinessGoalsClarity businessGoalsClarity;

    private List<BusinessMentoring> businessMentoringTypes;

    private StartupStage startupStage;

    private BusinessModelType businessModelType;

    private Boolean dpiitRegistered;

    private RegistrationType registrationType;

    private Integer totalTeamSize;

    private Integer fullTimeEmployees;

    private Integer partTimeEmployees;

    private List<KeyTeamMemberDTO> keyTeamMembers;

    private List<SupportNeeded> supportNeeded;

    // IV. Market & Customers

    private String targetCustomerSegment;

    private Boolean customerSegmentClarityNeeded;

    private List<PrimaryChallenge> primaryChallenges;

    private List<SupportRequired> supportRequired;

    private RevenueModel revenueModel;

    private BigDecimal monthlyRevenue;

    // V. Technology & Innovation

    private ProductReadinessLevel productReadinessLevel;

    private List<ProductDevelopmentSupport> productDevelopmentSupports;

    private String coreTechnologyUsed;

    private String ipsFiledOrPlanned;

    private Boolean technicalInfrastructureNeeded;

    private String technicalInfrastructureDescription;

    private List<DigitalMaturity> digitalMaturityTools;

    private List<TechnicalInfrastructureSupport> technicalInfrastructureSupports;

    private Boolean infraRelatedIssues;

    private List<InfrastructureAssistance> infrastructureAssistance;

    // Audit Fields

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private String createdBy;

    private String updatedBy;
}

