package com.metaverse.workflow.aleap_participant.service;

import com.metaverse.workflow.common.util.DateUtil;
import com.metaverse.workflow.model.AleapParticipant;
import jdk.jfr.Event;

import java.util.List;

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

        List<EventRes> events = entity.getEvents() == null ? List.of() :
                entity.getEvents().stream()
                        .map(event -> EventRes.builder()
                                .eventId(event.getEventId())
                                .eventTitle(event.getEventTitle())
                                .build())
                        .toList();

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
                .events(events)
                .build();
    }
    public static void updateEntity(AleapParticipant entity, AleapParticipantRequest request) {

        entity.setParticipantName(request.getParticipantName() != null ? request.getParticipantName() : entity.getParticipantName());
        entity.setGender(request.getGender() != null ? request.getGender() : entity.getGender());
        entity.setCategory(request.getCategory() != null ? request.getCategory() : entity.getCategory());
        entity.setDisability(request.getDisability() != null ? request.getDisability() : entity.getDisability());
        entity.setDob(request.getDob() != null ? DateUtil.stringToDates(request.getDob()) : entity.getDob());
        entity.setAge(request.getAge() != null ? request.getAge() : entity.getAge());
        entity.setAadharNo(request.getAadharNo() != null ? request.getAadharNo() : entity.getAadharNo());
        entity.setContactNo(request.getContactNo() != null ? request.getContactNo() : entity.getContactNo());
        entity.setEmail(request.getEmail() != null ? request.getEmail() : entity.getEmail());
        entity.setOrganizationName(request.getOrganizationName() != null ? request.getOrganizationName() : entity.getOrganizationName());
        entity.setDesignation(request.getDesignation() != null ? request.getDesignation() : entity.getDesignation());
        entity.setState(request.getState() != null ? request.getState() : entity.getState());
        entity.setDistrict(request.getDistrict() != null ? request.getDistrict() : entity.getDistrict());
        entity.setMandal(request.getMandal() != null ? request.getMandal() : entity.getMandal());
        entity.setVillage(request.getVillage() != null ? request.getVillage() : entity.getVillage());
        entity.setStreetOrBlock(request.getStreetOrBlock() != null ? request.getStreetOrBlock() : entity.getStreetOrBlock());
        entity.setHouseNoOrDoorNo(request.getHouseNoOrDoorNo() != null ? request.getHouseNoOrDoorNo() : entity.getHouseNoOrDoorNo());
        entity.setPinCode(request.getPinCode() != null ? request.getPinCode() : entity.getPinCode());
        entity.setOtherInformation(request.getOtherInformation() != null ? request.getOtherInformation() : entity.getOtherInformation());
    }
}