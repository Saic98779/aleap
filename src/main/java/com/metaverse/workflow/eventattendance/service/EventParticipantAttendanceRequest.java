package com.metaverse.workflow.eventattendance.service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EventParticipantAttendanceRequest {

    private Long eventId;

    private Long participantId;

    private Character[] attendanceData;
}