package com.metaverse.workflow.eventattendance.service;


import com.metaverse.workflow.model.EventAttendance;
import com.metaverse.workflow.model.EventAttendanceId;
import com.metaverse.workflow.programattendance.util.AttendanceUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EventAttendanceRequestMapper {

    public static List<EventAttendance> map(EventAttendanceRequest request) {

        if (request == null || request.getParticipantAttendanceList() == null) {
            return Collections.emptyList();
        }

        List<EventAttendance> list = new ArrayList<>();

        for (EventAttendanceRequest.ParticipantAttendance attendance :
                request.getParticipantAttendanceList()) {

            list.add(createEventAttendance(
                    request.getEventId(),
                    attendance.getParticipantId(),
                    attendance.getAttendanceData()
            ));
        }

        return list;
    }

    public static EventAttendance mapSingleParticipant(
            EventParticipantAttendanceRequest request) {

        if (request == null) {
            return null;
        }

        return createEventAttendance(
                request.getEventId(),
                request.getParticipantId(),
                request.getAttendanceData()
        );
    }

    private static EventAttendance createEventAttendance(
            Long eventId,
            Long participantId,
            Character[] attendanceData) {

        return EventAttendance.builder()
                .eventAttendanceId(
                        EventAttendanceId.builder()
                                .eventId(eventId)
                                .aleapParticipantId(participantId)
                                .build()
                )
                .eventAttendanceData(
                        AttendanceUtil.characterArrayToString(attendanceData)
                )
                .build();
    }
}
