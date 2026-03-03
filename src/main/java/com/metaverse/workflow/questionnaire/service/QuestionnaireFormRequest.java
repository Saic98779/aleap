package com.metaverse.workflow.questionnaire.service;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class QuestionnaireFormRequest {

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
    private String positionInShg;
    private String mandalFederationName;
    private String voName;

    private String sourceOfIncome;
    private String skills;
    private Boolean willingToStartBusiness;
    private String businessType;
    private Boolean availableForTraining;
    private Boolean willingToWorkWithMsme;

    private List<String> govtSchemes;
    private List<String> digitalPlatforms;
    private List<String> certifications;
    private List<String> financialLinkages;
}