package com.metaverse.workflow.eventattendance.service;

import com.metaverse.workflow.aleap_participant.repository.AleapParticipantRepository;
import com.metaverse.workflow.common.response.WorkflowResponse;
import com.metaverse.workflow.common.util.DateUtil;
import com.metaverse.workflow.event.repository.EventDetailsRepository;
import com.metaverse.workflow.eventattendance.repository.EventAttendanceRepository;
import com.metaverse.workflow.model.AleapParticipant;
import com.metaverse.workflow.model.EventAttendance;
import com.metaverse.workflow.model.EventDetails;
import com.metaverse.workflow.programattendance.util.AttendanceUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class EventAttendanceService {
    private final EventDetailsRepository eventDetailsRepository;
    private final AleapParticipantRepository aleapParticipantRepository;
    private final EventAttendanceRepository eventAttendanceRepository;

    public WorkflowResponse attendanceByEventId(Long eventId, int page, int size) {

        Optional<EventDetails> event = eventDetailsRepository.findById(eventId);
        if (event.isEmpty()) {
            return WorkflowResponse.builder()
                    .status(400)
                    .message("Invalid Event")
                    .build();
        }

        if (event.get().getEventSessionsList() == null) {
            return WorkflowResponse.builder()
                    .status(400)
                    .message("No sessions created for event")
                    .build();
        }

        Set<String> dateSet = event.get().getEventSessionsList().stream()
                .map(session -> DateUtil.dateToString(
                        session.getSessionDate(),
                        "dd-MM-yyyy"))
                .collect(Collectors.toSet());

        List<AleapParticipant> participants;
        long totalElements;
        int totalPages;

        if (page == 0 && size == 0) {
            participants = aleapParticipantRepository.findByEvents_EventId(eventId);
            totalElements = participants.size();
            totalPages = 1;
        } else {
            Pageable pageable = PageRequest.of(page, size);
            Page<AleapParticipant> participantPage = aleapParticipantRepository.findByEvents_EventId(eventId, pageable);
            participants = participantPage.getContent();
            totalElements = participantPage.getTotalElements();
            totalPages = participantPage.getTotalPages();
        }

        EventAttendanceResponse response = populateParticipantAttendance(
                        eventId,
                        participants,
                        dateSet.size()
        );

        List<EventAttendance> attendanceList = eventAttendanceRepository.findByEventAttendances(eventId);
        if (attendanceList != null && !attendanceList.isEmpty()) {
            response = updateParticipantAttendances(attendanceList, response);
        }

        return WorkflowResponse.builder()
                .status(200)
                .message("Success")
                .data(response)
                .totalElements(totalElements)
                .totalPages(totalPages)
                .build();
    }

    public WorkflowResponse updateEventAttendance(EventAttendanceRequest request) {
        List<EventAttendance> attendanceList = EventAttendanceRequestMapper.map(request);
        List<EventAttendance> response = eventAttendanceRepository.saveAll(attendanceList);
        return WorkflowResponse.builder()
                .status(200)
                .message("Success")
                .data(EventAttendanceResponseMapper.map(response))
                .build();
    }


    public WorkflowResponse updateParticipantAttendance(EventParticipantAttendanceRequest request) {
        WorkflowResponse validationResponse = validateEventAndParticipant(
                request.getEventId(),
                request.getParticipantId());
        if (validationResponse != null) {
            return validationResponse;
        }

        EventAttendance attendance = EventAttendanceRequestMapper.mapSingleParticipant(request);
        EventAttendance savedAttendance = eventAttendanceRepository.save(attendance);

        return WorkflowResponse.builder()
                .status(200)
                .message("Success")
                .data(EventAttendanceResponseMapper.map(
                        Collections.singletonList(savedAttendance)))
                .build();
    }

    private WorkflowResponse validateEventAndParticipant(Long eventId, Long participantId) {

        Optional<EventDetails> event = eventDetailsRepository.findById(eventId);
        if (event.isEmpty()) {
            return WorkflowResponse.builder()
                    .status(400)
                    .message("Invalid Event")
                    .build();
        }

        Optional<AleapParticipant> participant = aleapParticipantRepository.findById(participantId);

        if (participant.isEmpty()) {
            return WorkflowResponse.builder()
                    .status(400)
                    .message("Invalid Participant")
                    .build();
        }

        boolean isEnrolled = participant.get()
                .getEvents()
                .stream()
                .anyMatch(e -> e.getEventId().equals(eventId));

        if (!isEnrolled) {
            return WorkflowResponse.builder()
                    .status(400)
                    .message("Participant not enrolled in this event")
                    .build();
        }

        return null;
    }

    private EventAttendanceResponse updateParticipantAttendances(List<EventAttendance> list, EventAttendanceResponse response) {

        Map<Long, String> existingDetailsMap =
                list.stream()
                        .collect(Collectors.toMap(
                                details -> details.getEventAttendanceId().getAleapParticipantId(),
                                EventAttendance::getEventAttendanceData
                        ));

        for (EventAttendanceResponse.ParticipantAttendance attendance : response.getParticipantAttendanceList()) {

            String attendances = existingDetailsMap.get(attendance.getParticipantId());
            if (attendances != null) {
                attendance.setAttendanceData(AttendanceUtil.stringToCharacterArray(attendances));
            }
        }

        return response;
    }

    private EventAttendanceResponse populateParticipantAttendance(Long eventId, List<AleapParticipant> participants, Integer days) {
        List<EventAttendanceResponse.ParticipantAttendance> list =
                participants.stream()
                        .map(participant ->
                                EventAttendanceResponse.ParticipantAttendance.builder()
                                        .participantId(participant.getId())
                                        .participantName(participant.getParticipantName())
                                        .email(participant.getEmail())
                                        .designation(participant.getDesignation())
                                        .attendanceData(populateAttendaceData(days))
                                        .build())
                        .collect(Collectors.toList());

        return EventAttendanceResponse.builder()
                .eventId(eventId)
                .participantAttendanceList(list)
                .build();
    }

    private Character[] populateAttendaceData(Integer days) {
        Character[] charArray = new Character[days];
        Character defaultChar = (days == 1) ? 'P' : 'A';
        Arrays.fill(charArray, defaultChar);
        return charArray;
    }
}
