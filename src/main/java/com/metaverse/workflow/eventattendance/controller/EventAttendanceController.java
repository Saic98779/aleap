package com.metaverse.workflow.eventattendance.controller;


import com.metaverse.workflow.common.response.WorkflowResponse;
import com.metaverse.workflow.eventattendance.service.EventAttendanceRequest;
import com.metaverse.workflow.eventattendance.service.EventAttendanceService;
import com.metaverse.workflow.eventattendance.service.EventParticipantAttendanceRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class EventAttendanceController {

    private final EventAttendanceService eventAttendanceService;

    public EventAttendanceController(EventAttendanceService eventAttendanceService) {
        this.eventAttendanceService = eventAttendanceService;
    }

    @GetMapping("/event/attendance/{eventId}")
    public ResponseEntity<WorkflowResponse> attendanceByEventId(
            @PathVariable("eventId") Long eventId,
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "size", required = false) Integer size) {

        log.info("Event attendance controller, eventId : {}", eventId);

        WorkflowResponse response =
                eventAttendanceService.attendanceByEventId(
                        eventId,
                        page != null ? page : 0,
                        size != null ? size : 0);

        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "/event/attendance",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WorkflowResponse> saveEventAttendance(
            @RequestBody EventAttendanceRequest request) {

        log.info("Event attendance controller, eventId : {}",
                request.getEventId());

        WorkflowResponse response =
                eventAttendanceService.updateEventAttendance(request);

        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "/event/participant/attendance",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WorkflowResponse> saveParticipantAttendance(
            @RequestBody EventParticipantAttendanceRequest request) {

        log.info("Event participant attendance controller, eventId: {}, participantId: {}",
                request.getEventId(),
                request.getParticipantId());

        WorkflowResponse response =
                eventAttendanceService.updateParticipantAttendance(request);

        return ResponseEntity.ok(response);
    }
}
