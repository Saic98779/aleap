package com.metaverse.workflow.aleap_participant.service;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EventRes {
    private Long eventId;
    private String eventTitle;
}
