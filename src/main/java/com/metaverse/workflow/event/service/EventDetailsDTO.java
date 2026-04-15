package com.metaverse.workflow.event.service;

import com.metaverse.workflow.common.enums.EventType;
import com.metaverse.workflow.common.enums.ImplementingAgency;
import lombok.*;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventDetailsDTO {

    private Long eventId;
    private EventType eventType;
    private String eventTitle;
    private String description;
    private String projectName;
    private String fundingAgency;
    private String ministry;
    private ImplementingAgency implementingAgency;
    private String programCoordinatorName;
    private String designation;
    private String startDate;
    private String endDate;
    private Integer totalDays;
    private String startTime;
    private String endTime;
    private String state;
    private String district;
    private String mandal;
    private String village;
    private String streetOrBlock;
    private String houseNoOrDoorNo;
    private String pinCode;
    private Integer totalParticipants;
}
