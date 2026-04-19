package com.metaverse.workflow.aleap_participant.service;

import lombok.Data;

import java.util.List;

@Data
public class AleapParticipantRequest {

    private String participantName;
    private String gender;
    private String category;
    private String disability;

    private String dob;
    private Integer age;

    private String aadharNo;
    private Long contactNo;
    private String email;

    private String organizationName;
    private String designation;

    private String state;
    private String district;
    private String mandal;
    private String village;
    private String streetOrBlock;
    private String houseNoOrDoorNo;
    private String pinCode;

    private String otherInformation;
    private List<Long> eventIds;
}