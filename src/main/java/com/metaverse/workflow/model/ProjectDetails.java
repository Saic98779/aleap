package com.metaverse.workflow.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Table(name = "project_details")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titleOfProject;

    private String fundingAgency;

    private String ministryOrConcernedDepartment;

    private String spocName;

    private String spocDesignation;

    private String spocEmail;

    private Long spocContact;

    private Double projectCostInLakhs;

    private Date startDate;

    private Date endDate;

    private String projectHeadAndTeam;

    @Column(length = 3000)
    private String briefDescription;

    private String projectLocation;

    private Integer totalNoOfBeneficiaries;

    @Column(length = 2000)
    private String expectedImpactOrOutcome;

    private String sanctionOrderFilePath;

    private String beneficiariesUploadFilePath;

    @CreationTimestamp
    @Column(updatable = false)
    private Date createdOn;

    @UpdateTimestamp
    private Date updatedOn;
}
