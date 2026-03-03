package com.metaverse.workflow.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "questionnaire_form")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionnaireForm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String fatherOrHusbandName;

    private Date dateOfBirth;

    private Integer age;
    private String qualification;
    private String emailId;
    private Long phoneNo;
    private Long alternatePhoneNo;

    private String gender;
    private String caste;
    private Boolean disability;

    private String doorNo;
    private String streetName;
    private String village;
    private String panchayatName;
    private String mandal;
    private String district;
    private String state;
    private Integer pincode;

    private String aadharNo;
    private String panCard;

    private String shgName;
    private Integer yearsSpentInShg;
    private String shgLeaderName;
    private String positionInShg; // Member / First Leader / Second Leader
    private String mandalFederationName;
    private String voName;

    private String sourceOfIncome;
    private String skills;
    private Boolean willingToStartBusiness;
    private String businessType;
    private Boolean availableForTraining;
    private Boolean willingToWorkWithMsme;

    @ElementCollection
    @CollectionTable(name = "questionnaire_govt_schemes",
            joinColumns = @JoinColumn(name = "questionnaire_id"))
    @Column(name = "scheme")
    private List<String> govtSchemes;

    @ElementCollection
    @CollectionTable(name = "questionnaire_digital_platforms",
            joinColumns = @JoinColumn(name = "questionnaire_id"))
    @Column(name = "platform")
    private List<String> digitalPlatforms;

    @ElementCollection
    @CollectionTable(name = "questionnaire_certifications",
            joinColumns = @JoinColumn(name = "questionnaire_id"))
    @Column(name = "certification")
    private List<String> certifications;

    @ElementCollection
    @CollectionTable(name = "questionnaire_financial_linkages",
            joinColumns = @JoinColumn(name = "questionnaire_id"))
    @Column(name = "financial_linkage")
    private List<String> financialLinkages;

    @CreationTimestamp
    @Column(updatable = false)
    private Date createdOn;

    @UpdateTimestamp
    private Date updatedOn;
}