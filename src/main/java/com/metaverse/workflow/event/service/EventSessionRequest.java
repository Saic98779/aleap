package com.metaverse.workflow.event.service;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventSessionRequest {

    private Long eventId;

    private Date sessionDate;

    private String startTime;

    private String endTime;

    private String sessionTypeName;

    private String sessionTypeMethodology;

    private String sessionDetails;

    private Long resourceId;

    private String sessionStreamingUrl;

    private String image1;

    private String image2;

    private String image3;

    private String image4;

    private String image5;
}