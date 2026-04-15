package com.metaverse.workflow.aleap_participant.service;

import com.metaverse.workflow.common.util.DateUtil;
import com.metaverse.workflow.model.AleapParticipant;

public class AleapParticipantMapper {

    public static AleapParticipant mapToEntity(AleapParticipantRequest request) {

        return AleapParticipant.builder()
                .participantName(request.getParticipantName())
                .gender(request.getGender())
                .category(request.getCategory())
                .disability(request.getDisability())
                .dob(DateUtil.stringToDates(request.getDob()))
                .age(request.getAge())
                .aadharNo(request.getAadharNo())
                .contactNo(request.getContactNo())
                .email(request.getEmail())
                .organizationName(request.getOrganizationName())
                .designation(request.getDesignation())
                .state(request.getState())
                .district(request.getDistrict())
                .mandal(request.getMandal())
                .village(request.getVillage())
                .streetOrBlock(request.getStreetOrBlock())
                .houseNoOrDoorNo(request.getHouseNoOrDoorNo())
                .pinCode(request.getPinCode())
                .otherInformation(request.getOtherInformation())
                .build();
    }

    public static AleapParticipantResponse mapToResponse(AleapParticipant entity) {

        return AleapParticipantResponse.builder()
                .id(entity.getId())
                .participantName(entity.getParticipantName())
                .gender(entity.getGender())
                .category(entity.getCategory())
                .disability(entity.getDisability())
                .dob(DateUtil.dateToString(entity.getDob(),"yyyy-MM-dd"))
                .age(entity.getAge())
                .aadharNo(entity.getAadharNo())
                .contactNo(entity.getContactNo())
                .email(entity.getEmail())
                .organizationName(entity.getOrganizationName())
                .designation(entity.getDesignation())
                .state(entity.getState())
                .district(entity.getDistrict())
                .mandal(entity.getMandal())
                .village(entity.getVillage())
                .streetOrBlock(entity.getStreetOrBlock())
                .houseNoOrDoorNo(entity.getHouseNoOrDoorNo())
                .pinCode(entity.getPinCode())
                .otherInformation(entity.getOtherInformation())
                .build();
    }

    public static void updateEntity(AleapParticipant entity, AleapParticipantRequest request) {

        entity.setParticipantName(request.getParticipantName());
        entity.setGender(request.getGender());
        entity.setCategory(request.getCategory());
        entity.setDisability(request.getDisability());
        entity.setDob(DateUtil.stringToDates(request.getDob()));
        entity.setAge(request.getAge());
        entity.setAadharNo(request.getAadharNo());
        entity.setContactNo(request.getContactNo());
        entity.setEmail(request.getEmail());
        entity.setOrganizationName(request.getOrganizationName());
        entity.setDesignation(request.getDesignation());
        entity.setState(request.getState());
        entity.setDistrict(request.getDistrict());
        entity.setMandal(request.getMandal());
        entity.setVillage(request.getVillage());
        entity.setStreetOrBlock(request.getStreetOrBlock());
        entity.setHouseNoOrDoorNo(request.getHouseNoOrDoorNo());
        entity.setPinCode(request.getPinCode());
        entity.setOtherInformation(request.getOtherInformation());
    }
}