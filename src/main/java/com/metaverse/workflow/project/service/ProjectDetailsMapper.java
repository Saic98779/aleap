package com.metaverse.workflow.project.service;


import com.metaverse.workflow.model.ProjectDetails;

public class ProjectDetailsMapper {

    public static ProjectDetails mapToEntity(ProjectDetailsRequest request) {

        return ProjectDetails.builder()
                .titleOfProject(request.getTitleOfProject())
                .fundingAgency(request.getFundingAgency())
                .ministryOrConcernedDepartment(request.getMinistryOrConcernedDepartment())
                .spocName(request.getSpocName())
                .spocContact(request.getSpocContact())
                .spocDesignation(request.getSpocDesignation())
                .spocEmail(request.getSpocEmail())
                .projectCostInLakhs(request.getProjectCostInLakhs())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .projectHeadAndTeam(request.getProjectHeadAndTeam())
                .briefDescription(request.getBriefDescription())
                .projectLocation(request.getProjectLocation())
                .totalNoOfBeneficiaries(request.getTotalNoOfBeneficiaries())
                .expectedImpactOrOutcome(request.getExpectedImpactOrOutcome())
                .sanctionOrderFilePath(request.getSanctionOrderFilePath())
                .beneficiariesUploadFilePath(request.getBeneficiariesUploadFilePath())
                .build();
    }

    public static ProjectDetailsResponse mapToResponse(ProjectDetails entity) {

        return ProjectDetailsResponse.builder()
                .id(entity.getId())
                .titleOfProject(entity.getTitleOfProject())
                .fundingAgency(entity.getFundingAgency())
                .ministryOrConcernedDepartment(entity.getMinistryOrConcernedDepartment())
                .spocDetails(String.join(" ",
                        entity.getSpocName(),
                        entity.getSpocDesignation(),
                        entity.getSpocContact() != null ? entity.getSpocContact().toString() : "",
                        entity.getSpocEmail()))
                .spocName(entity.getSpocName())
                .spocDesignation(entity.getSpocDesignation())
                .spocContact(entity.getSpocContact())
                .spocEmail(entity.getSpocEmail())
                .projectCostInLakhs(entity.getProjectCostInLakhs())
                .startDate(entity.getStartDate())
                .endDate(entity.getEndDate())
                .projectHeadAndTeam(entity.getProjectHeadAndTeam())
                .briefDescription(entity.getBriefDescription())
                .projectLocation(entity.getProjectLocation())
                .totalNoOfBeneficiaries(entity.getTotalNoOfBeneficiaries())
                .expectedImpactOrOutcome(entity.getExpectedImpactOrOutcome())
                .sanctionOrderFilePath(entity.getSanctionOrderFilePath())
                .beneficiariesUploadFilePath(entity.getBeneficiariesUploadFilePath())
                .createdOn(entity.getCreatedOn())
                .updatedOn(entity.getUpdatedOn())
                .build();
    }
}
