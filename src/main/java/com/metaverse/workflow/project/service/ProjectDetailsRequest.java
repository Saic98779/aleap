package com.metaverse.workflow.project.service;



import com.metaverse.workflow.common.enums.ImplementingAgency;
import lombok.*;

import java.util.Date;

@Getter
@Setter
public class ProjectDetailsRequest {
    private String titleOfProject;
    private String fundingAgency;
    private String ministryOrConcernedDepartment;
    private String spocDetails;
    private String spocName;
    private String spocDesignation;
    private String spocEmail;
    private Long spocContact;
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
    private ImplementingAgency implementingAgency;
}
