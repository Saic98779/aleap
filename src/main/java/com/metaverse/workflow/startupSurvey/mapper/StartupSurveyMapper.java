package com.metaverse.workflow.startupSurvey.mapper;

import com.metaverse.workflow.startupSurvey.dto.KeyTeamMemberDTO;
import com.metaverse.workflow.startupSurvey.dto.StartupSurveyDTO;
import com.metaverse.workflow.startupSurvey.model.KeyTeamMember;
import com.metaverse.workflow.startupSurvey.model.StartupSurvey;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class StartupSurveyMapper {

    public StartupSurveyDTO toDTO(StartupSurvey startupSurvey) {
        if (startupSurvey == null) {
            return null;
        }

        return StartupSurveyDTO.builder()
                .id(startupSurvey.getId())
                .startupName(startupSurvey.getStartupName())
                .founderName(startupSurvey.getFounderName())
                .genderOfPrimaryFounder(startupSurvey.getGenderOfPrimaryFounder())
                .ageOfPrimaryFounder(startupSurvey.getAgeOfPrimaryFounder())
                .educationalQualification(startupSurvey.getEducationalQualification())
                .email(startupSurvey.getEmail())
                .phone(startupSurvey.getPhone())
                .city(startupSurvey.getCity())
                .district(startupSurvey.getDistrict())
                .state(startupSurvey.getState())
                .dateOfEstablishment(startupSurvey.getDateOfEstablishment())
                .sectorIndustry(startupSurvey.getSectorIndustry())
                .webSite(startupSurvey.getWebSite())
                .productServiceDescription(startupSurvey.getProductServiceDescription())
                .problemSolving(startupSurvey.getProblemSolving())
                .uniqueValueProposition(startupSurvey.getUniqueValueProposition())
                .businessPlanStatus(startupSurvey.getBusinessPlanStatus())
                .businessGoalsClarity(startupSurvey.getBusinessGoalsClarity())
                .businessMentoringTypes(startupSurvey.getBusinessMentoringTypes())
                .startupStage(startupSurvey.getStartupStage())
                .businessModelType(startupSurvey.getBusinessModelType())
                .dpiitRegistered(startupSurvey.getDpiitRegistered())
                .registrationType(startupSurvey.getRegistrationType())
                .totalTeamSize(startupSurvey.getTotalTeamSize())
                .fullTimeEmployees(startupSurvey.getFullTimeEmployees())
                .partTimeEmployees(startupSurvey.getPartTimeEmployees())
                .keyTeamMembers(startupSurvey.getKeyTeamMembers() != null ?
                        startupSurvey.getKeyTeamMembers().stream()
                                .map(this::keyTeamMemberToDTO)
                                .collect(Collectors.toList()) : null)
                .supportNeeded(startupSurvey.getSupportNeeded())
                .targetCustomerSegment(startupSurvey.getTargetCustomerSegment())
                .customerSegmentClarityNeeded(startupSurvey.getCustomerSegmentClarityNeeded())
                .primaryChallenges(startupSurvey.getPrimaryChallenges())
                .supportRequired(startupSurvey.getSupportRequired())
                .revenueModel(startupSurvey.getRevenueModel())
                .monthlyRevenue(startupSurvey.getMonthlyRevenue())
                .productReadinessLevel(startupSurvey.getProductReadinessLevel())
                .productDevelopmentSupports(startupSurvey.getProductDevelopmentSupports())
                .coreTechnologyUsed(startupSurvey.getCoreTechnologyUsed())
                .ipsFiledOrPlanned(startupSurvey.getIpsFiledOrPlanned())
                .technicalInfrastructureNeeded(startupSurvey.getTechnicalInfrastructureNeeded())
                .technicalInfrastructureDescription(startupSurvey.getTechnicalInfrastructureDescription())
                .digitalMaturityTools(startupSurvey.getDigitalMaturityTools())
                .technicalInfrastructureSupports(startupSurvey.getTechnicalInfrastructureSupports())
                .infraRelatedIssues(startupSurvey.getInfraRelatedIssues())
                .infrastructureAssistance(startupSurvey.getInfrastructureAssistance())
                .createdAt(startupSurvey.getCreatedAt())
                .updatedAt(startupSurvey.getUpdatedAt())
                .createdBy(startupSurvey.getCreatedBy())
                .updatedBy(startupSurvey.getUpdatedBy())
                .build();
    }

    public StartupSurvey toEntity(StartupSurveyDTO startupSurveyDTO) {
        if (startupSurveyDTO == null) {
            return null;
        }

        StartupSurvey.StartupSurveyBuilder builder = StartupSurvey.builder()
                .startupName(startupSurveyDTO.getStartupName())
                .founderName(startupSurveyDTO.getFounderName())
                .genderOfPrimaryFounder(startupSurveyDTO.getGenderOfPrimaryFounder())
                .ageOfPrimaryFounder(startupSurveyDTO.getAgeOfPrimaryFounder())
                .educationalQualification(startupSurveyDTO.getEducationalQualification())
                .email(startupSurveyDTO.getEmail())
                .phone(startupSurveyDTO.getPhone())
                .city(startupSurveyDTO.getCity())
                .district(startupSurveyDTO.getDistrict())
                .state(startupSurveyDTO.getState())
                .dateOfEstablishment(startupSurveyDTO.getDateOfEstablishment())
                .sectorIndustry(startupSurveyDTO.getSectorIndustry())
                .webSite(startupSurveyDTO.getWebSite())
                .productServiceDescription(startupSurveyDTO.getProductServiceDescription())
                .problemSolving(startupSurveyDTO.getProblemSolving())
                .uniqueValueProposition(startupSurveyDTO.getUniqueValueProposition())
                .businessPlanStatus(startupSurveyDTO.getBusinessPlanStatus())
                .businessGoalsClarity(startupSurveyDTO.getBusinessGoalsClarity())
                .businessMentoringTypes(startupSurveyDTO.getBusinessMentoringTypes())
                .startupStage(startupSurveyDTO.getStartupStage())
                .businessModelType(startupSurveyDTO.getBusinessModelType())
                .dpiitRegistered(startupSurveyDTO.getDpiitRegistered())
                .registrationType(startupSurveyDTO.getRegistrationType())
                .totalTeamSize(startupSurveyDTO.getTotalTeamSize())
                .fullTimeEmployees(startupSurveyDTO.getFullTimeEmployees())
                .partTimeEmployees(startupSurveyDTO.getPartTimeEmployees())
                .supportNeeded(startupSurveyDTO.getSupportNeeded())
                .targetCustomerSegment(startupSurveyDTO.getTargetCustomerSegment())
                .customerSegmentClarityNeeded(startupSurveyDTO.getCustomerSegmentClarityNeeded())
                .primaryChallenges(startupSurveyDTO.getPrimaryChallenges())
                .supportRequired(startupSurveyDTO.getSupportRequired())
                .revenueModel(startupSurveyDTO.getRevenueModel())
                .monthlyRevenue(startupSurveyDTO.getMonthlyRevenue())
                .productReadinessLevel(startupSurveyDTO.getProductReadinessLevel())
                .productDevelopmentSupports(startupSurveyDTO.getProductDevelopmentSupports())
                .coreTechnologyUsed(startupSurveyDTO.getCoreTechnologyUsed())
                .ipsFiledOrPlanned(startupSurveyDTO.getIpsFiledOrPlanned())
                .technicalInfrastructureNeeded(startupSurveyDTO.getTechnicalInfrastructureNeeded())
                .technicalInfrastructureDescription(startupSurveyDTO.getTechnicalInfrastructureDescription())
                .digitalMaturityTools(startupSurveyDTO.getDigitalMaturityTools())
                .technicalInfrastructureSupports(startupSurveyDTO.getTechnicalInfrastructureSupports())
                .infraRelatedIssues(startupSurveyDTO.getInfraRelatedIssues())
                .infrastructureAssistance(startupSurveyDTO.getInfrastructureAssistance());

        StartupSurvey startupSurvey = builder.build();

        // if DTO had id, set it explicitly (Lombok builder doesn't include inherited id)
        if (startupSurveyDTO.getId() != null) {
            startupSurvey.setId(startupSurveyDTO.getId());
        }

        // map key team members if present and attach parent reference
        if (startupSurveyDTO.getKeyTeamMembers() != null) {
            List<KeyTeamMember> members = startupSurveyDTO.getKeyTeamMembers().stream()
                    .map(this::keyTeamMemberToEntity)
                    .collect(Collectors.toList());

            // set parent reference
            members.forEach(m -> m.setStartupSurvey(startupSurvey));
            startupSurvey.setKeyTeamMembers(members);
        }

        return startupSurvey;
    }

    public KeyTeamMemberDTO keyTeamMemberToDTO(KeyTeamMember keyTeamMember) {
        if (keyTeamMember == null) {
            return null;
        }

        return KeyTeamMemberDTO.builder()
                .id(keyTeamMember.getId())
                .startupSurveyId(keyTeamMember.getStartupSurvey() != null ? keyTeamMember.getStartupSurvey().getId() : null)
                .name(keyTeamMember.getName())
                .designation(keyTeamMember.getDesignation())
                .keyTasks(keyTeamMember.getKeyTasks())
                .createdAt(keyTeamMember.getCreatedAt())
                .updatedAt(keyTeamMember.getUpdatedAt())
                .createdBy(keyTeamMember.getCreatedBy())
                .updatedBy(keyTeamMember.getUpdatedBy())
                .build();
    }

    public KeyTeamMember keyTeamMemberToEntity(KeyTeamMemberDTO keyTeamMemberDTO) {
        if (keyTeamMemberDTO == null) {
            return null;
        }

        KeyTeamMember.KeyTeamMemberBuilder builder = KeyTeamMember.builder()
                .name(keyTeamMemberDTO.getName())
                .designation(keyTeamMemberDTO.getDesignation())
                .keyTasks(keyTeamMemberDTO.getKeyTasks());

        KeyTeamMember member = builder.build();

        // If id provided, set it via setter (builder doesn't include inherited id)
        if (keyTeamMemberDTO.getId() != null) {
            member.setId(keyTeamMemberDTO.getId());
        }

        // If startupSurveyId provided, only set the id reference (avoid loading entity here)
        if (keyTeamMemberDTO.getStartupSurveyId() != null) {
            StartupSurvey parent = new StartupSurvey();
            parent.setId(keyTeamMemberDTO.getStartupSurveyId());
            member.setStartupSurvey(parent);
        }

        return member;
    }
}
