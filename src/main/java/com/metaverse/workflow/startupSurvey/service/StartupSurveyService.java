package com.metaverse.workflow.startupSurvey.service;

import com.metaverse.workflow.startupSurvey.dto.StartupSurveyDTO;
import com.metaverse.workflow.startupSurvey.mapper.StartupSurveyMapper;
import com.metaverse.workflow.startupSurvey.model.StartupSurvey;
import com.metaverse.workflow.startupSurvey.repository.StartupSurveyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class StartupSurveyService {

    private final StartupSurveyRepository startupSurveyRepository;
    private final StartupSurveyMapper startupSurveyMapper;

    /**
     * Save or update startup survey as draft
     * Supports partial updates for draft mode
     */
    public StartupSurveyDTO saveDraft(StartupSurveyDTO startupSurveyDTO) {
        StartupSurvey startupSurvey;

        if (startupSurveyDTO.getId() != null) {
            // Update existing draft
            startupSurvey = startupSurveyRepository.findById(startupSurveyDTO.getId())
                    .orElseThrow(() -> new RuntimeException("Startup Survey not found with id: " + startupSurveyDTO.getId()));

            // Update only non-null fields
            updateSurveyFields(startupSurvey, startupSurveyDTO);
        } else {
            // Create new draft
            startupSurvey = startupSurveyMapper.toEntity(startupSurveyDTO);
        }

        StartupSurvey savedSurvey = startupSurveyRepository.save(startupSurvey);
        return startupSurveyMapper.toDTO(savedSurvey);
    }

    /**
     * Get survey by ID
     */
    public StartupSurveyDTO getSurveyById(Long id) {
        StartupSurvey startupSurvey = startupSurveyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Startup Survey not found with id: " + id));
        return startupSurveyMapper.toDTO(startupSurvey);
    }

    /**
     * Get all surveys with pagination
     */
    public Page<StartupSurveyDTO> getAllSurveys(Pageable pageable) {
        return startupSurveyRepository.findAll(pageable)
                .map(startupSurveyMapper::toDTO);
    }

    /**
     * Update survey (full update)
     */
    public StartupSurveyDTO updateSurvey(Long id, StartupSurveyDTO startupSurveyDTO) {
        StartupSurvey startupSurvey = startupSurveyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Startup Survey not found with id: " + id));

        startupSurveyDTO.setId(id);
        updateSurveyFields(startupSurvey, startupSurveyDTO);

        StartupSurvey updatedSurvey = startupSurveyRepository.save(startupSurvey);
        return startupSurveyMapper.toDTO(updatedSurvey);
    }

    /**
     * Delete survey by ID
     */
    public void deleteSurvey(Long id) {
        if (!startupSurveyRepository.existsById(id)) {
            throw new RuntimeException("Startup Survey not found with id: " + id);
        }
        startupSurveyRepository.deleteById(id);
    }

    /**
     * Helper method to update survey fields
     */
    private void updateSurveyFields(StartupSurvey existingSurvey, StartupSurveyDTO dto) {
        if (dto.getStartupName() != null) existingSurvey.setStartupName(dto.getStartupName());
        if (dto.getFounderName() != null) existingSurvey.setFounderName(dto.getFounderName());
        if (dto.getGenderOfPrimaryFounder() != null) existingSurvey.setGenderOfPrimaryFounder(dto.getGenderOfPrimaryFounder());
        if (dto.getAgeOfPrimaryFounder() != null) existingSurvey.setAgeOfPrimaryFounder(dto.getAgeOfPrimaryFounder());
        if (dto.getEducationalQualification() != null) existingSurvey.setEducationalQualification(dto.getEducationalQualification());
        if (dto.getEmail() != null) existingSurvey.setEmail(dto.getEmail());
        if (dto.getPhone() != null) existingSurvey.setPhone(dto.getPhone());
        if (dto.getCity() != null) existingSurvey.setCity(dto.getCity());
        if (dto.getDistrict() != null) existingSurvey.setDistrict(dto.getDistrict());
        if (dto.getState() != null) existingSurvey.setState(dto.getState());
        if (dto.getDateOfEstablishment() != null) existingSurvey.setDateOfEstablishment(dto.getDateOfEstablishment());
        if (dto.getSectorIndustry() != null) existingSurvey.setSectorIndustry(dto.getSectorIndustry());
        if (dto.getWebSite() != null) existingSurvey.setWebSite(dto.getWebSite());
        if (dto.getProductServiceDescription() != null) existingSurvey.setProductServiceDescription(dto.getProductServiceDescription());
        if (dto.getProblemSolving() != null) existingSurvey.setProblemSolving(dto.getProblemSolving());
        if (dto.getUniqueValueProposition() != null) existingSurvey.setUniqueValueProposition(dto.getUniqueValueProposition());
        if (dto.getBusinessPlanStatus() != null) existingSurvey.setBusinessPlanStatus(dto.getBusinessPlanStatus());
        if (dto.getBusinessGoalsClarity() != null) existingSurvey.setBusinessGoalsClarity(dto.getBusinessGoalsClarity());
        if (dto.getBusinessMentoringTypes() != null) existingSurvey.setBusinessMentoringTypes(dto.getBusinessMentoringTypes());
        if (dto.getStartupStage() != null) existingSurvey.setStartupStage(dto.getStartupStage());
        if (dto.getBusinessModelType() != null) existingSurvey.setBusinessModelType(dto.getBusinessModelType());
        if (dto.getDpiitRegistered() != null) existingSurvey.setDpiitRegistered(dto.getDpiitRegistered());
        if (dto.getRegistrationType() != null) existingSurvey.setRegistrationType(dto.getRegistrationType());
        if (dto.getTotalTeamSize() != null) existingSurvey.setTotalTeamSize(dto.getTotalTeamSize());
        if (dto.getFullTimeEmployees() != null) existingSurvey.setFullTimeEmployees(dto.getFullTimeEmployees());
        if (dto.getPartTimeEmployees() != null) existingSurvey.setPartTimeEmployees(dto.getPartTimeEmployees());
        if (dto.getTargetCustomerSegment() != null) existingSurvey.setTargetCustomerSegment(dto.getTargetCustomerSegment());
        if (dto.getCustomerSegmentClarityNeeded() != null) existingSurvey.setCustomerSegmentClarityNeeded(dto.getCustomerSegmentClarityNeeded());
        if (dto.getPrimaryChallenges() != null) existingSurvey.setPrimaryChallenges(dto.getPrimaryChallenges());
        if (dto.getSupportRequired() != null) existingSurvey.setSupportRequired(dto.getSupportRequired());
        if (dto.getRevenueModel() != null) existingSurvey.setRevenueModel(dto.getRevenueModel());
        if (dto.getMonthlyRevenue() != null) existingSurvey.setMonthlyRevenue(dto.getMonthlyRevenue());
        if (dto.getProductReadinessLevel() != null) existingSurvey.setProductReadinessLevel(dto.getProductReadinessLevel());
        if (dto.getProductDevelopmentSupports() != null) existingSurvey.setProductDevelopmentSupports(dto.getProductDevelopmentSupports());
        if (dto.getCoreTechnologyUsed() != null) existingSurvey.setCoreTechnologyUsed(dto.getCoreTechnologyUsed());
        if (dto.getIpsFiledOrPlanned() != null) existingSurvey.setIpsFiledOrPlanned(dto.getIpsFiledOrPlanned());
        if (dto.getTechnicalInfrastructureNeeded() != null) existingSurvey.setTechnicalInfrastructureNeeded(dto.getTechnicalInfrastructureNeeded());
        if (dto.getTechnicalInfrastructureDescription() != null) existingSurvey.setTechnicalInfrastructureDescription(dto.getTechnicalInfrastructureDescription());
        if (dto.getDigitalMaturityTools() != null) existingSurvey.setDigitalMaturityTools(dto.getDigitalMaturityTools());
        if (dto.getTechnicalInfrastructureSupports() != null) existingSurvey.setTechnicalInfrastructureSupports(dto.getTechnicalInfrastructureSupports());
        if (dto.getInfraRelatedIssues() != null) existingSurvey.setInfraRelatedIssues(dto.getInfraRelatedIssues());
        if (dto.getInfrastructureAssistance() != null) existingSurvey.setInfrastructureAssistance(dto.getInfrastructureAssistance());
        if (dto.getSupportNeeded() != null) existingSurvey.setSupportNeeded(dto.getSupportNeeded());
    }
}
