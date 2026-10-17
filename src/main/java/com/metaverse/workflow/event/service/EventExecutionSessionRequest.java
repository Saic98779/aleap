package com.metaverse.workflow.event.service;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventExecutionSessionRequest {

    private Long eventId;

    private Long programSessionId;

    private Long sessionId;

    private String sessionDate;

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
