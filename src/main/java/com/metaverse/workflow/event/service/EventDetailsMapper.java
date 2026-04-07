package com.metaverse.workflow.event.service;


import com.metaverse.workflow.common.util.DateUtil;
import com.metaverse.workflow.model.EventDetails;

public class EventDetailsMapper {

    public static EventDetailsDTO mapToEventDetailsRes(EventDetails entity) {
        return EventDetailsDTO.builder()
                .eventId(entity.getEventId())
                .eventType(entity.getEventType())
                .eventTitle(entity.getEventTitle())
                .description(entity.getDescription())
                .projectName(entity.getProjectName())
                .fundingAgency(entity.getFundingAgency())
                .ministry(entity.getMinistry())
                .implementingAgency(entity.getImplementingAgency())
                .programCoordinatorName(entity.getProgramCoordinatorName())
                .designation(entity.getDesignation())
                .startDate(entity.getStartDate().toString())
                .endDate(entity.getEndDate().toString())
                .totalDays(entity.getTotalDays())
                .startTime(entity.getStartTime())
                .endTime(entity.getEndTime())
                .district(entity.getDistrict())
                .mandal(entity.getMandal())
                .village(entity.getVillage())
                .pinCode(entity.getPinCode())
                .totalParticipants(entity.getTotalParticipants())
                .build();
    }

    public static EventDetails mapToEventDetails(EventDetailsDTO dto) {
        return EventDetails.builder()
                .eventId(dto.getEventId())
                .eventType(dto.getEventType())
                .eventTitle(dto.getEventTitle())
                .description(dto.getDescription())
                .projectName(dto.getProjectName())
                .fundingAgency(dto.getFundingAgency())
                .ministry(dto.getMinistry())
                .implementingAgency(dto.getImplementingAgency())
                .programCoordinatorName(dto.getProgramCoordinatorName())
                .designation(dto.getDesignation())
                .startDate(DateUtil.stringToDate(dto.getStartDate()))
                .endDate(DateUtil.stringToDate(dto.getEndDate()))
                .totalDays(dto.getTotalDays())
                .startTime(dto.getStartTime())
                .endTime(dto.getEndTime())
                .district(dto.getDistrict())
                .mandal(dto.getMandal())
                .village(dto.getVillage())
                .pinCode(dto.getPinCode())
                .totalParticipants(dto.getTotalParticipants())
                .build();
    }

    public static void mapToEntityForUpdate(EventDetailsDTO request, EventDetails entity) {

        entity.setEventType(request.getEventType());
        entity.setEventTitle(request.getEventTitle());
        entity.setDescription(request.getDescription());
        entity.setProjectName(request.getProjectName());
        entity.setFundingAgency(request.getFundingAgency());
        entity.setMinistry(request.getMinistry());
        entity.setImplementingAgency(request.getImplementingAgency());
        entity.setProgramCoordinatorName(request.getProgramCoordinatorName());
        entity.setDesignation(request.getDesignation());
        entity.setStartDate(DateUtil.stringToDate(request.getStartDate()));
        entity.setEndDate(DateUtil.stringToDate(request.getEndDate()));
        entity.setTotalDays(request.getTotalDays());
        entity.setStartTime(request.getStartTime());
        entity.setEndTime(request.getEndTime());
        entity.setDistrict(request.getDistrict());
        entity.setMandal(request.getMandal());
        entity.setVillage(request.getVillage());
        entity.setPinCode(request.getPinCode());
        entity.setTotalParticipants(request.getTotalParticipants());
    }
}
