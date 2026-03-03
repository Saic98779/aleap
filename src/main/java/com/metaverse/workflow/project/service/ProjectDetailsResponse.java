package com.metaverse.workflow.project.service;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
public class ProjectDetailsResponse {

    private Long id;
    private String titleOfProject;
    private String fundingAgency;
    private String ministryOrConcernedDepartment;
    private String spocDetails;
    private Double projectCostInLakhs;
    private Date startDate;
    private Date endDate;
    private String projectHeadAndTeam;
    private String briefDescription;
    private String projectLocation;
    private Integer totalNoOfBeneficiaries;
    private String expectedImpactOrOutcome;
    private String sanctionOrderFilePath;
    private String beneficiariesUploadFilePath;
    private Date createdOn;
    private Date updatedOn;
}
