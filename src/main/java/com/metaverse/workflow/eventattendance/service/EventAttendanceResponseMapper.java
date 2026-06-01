package com.metaverse.workflow.eventattendance.service;

import com.metaverse.workflow.model.EventAttendance;
import com.metaverse.workflow.programattendance.util.AttendanceUtil;

import java.util.ArrayList;
import java.util.List;

public class EventAttendanceResponseMapper {

    public static EventAttendanceResponse map(List<EventAttendance> attendanceList) {

        if (attendanceList == null || attendanceList.isEmpty()) {
            return EventAttendanceResponse.builder().build();
        }

        List<EventAttendanceResponse.ParticipantAttendance> list = new ArrayList<>();
        Long eventId = null;

        for (EventAttendance attendance : attendanceList) {
            eventId = attendance.getEventAttendanceId().getEventId();

            list.add(
                    EventAttendanceResponse.ParticipantAttendance.builder()
                            .participantId(attendance.getEventAttendanceId().getAleapParticipantId())
                            .attendanceData(
                                    AttendanceUtil.stringToCharacterArray(
                                            attendance.getEventAttendanceData()
                                    )
                            )
                            .build()
            );
        }

        return EventAttendanceResponse.builder()
                .eventId(eventId)
                .participantAttendanceList(list)
                .build();
    }
}
