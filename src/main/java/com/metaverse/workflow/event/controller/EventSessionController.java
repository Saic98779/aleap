package com.metaverse.workflow.event.controller;


import com.metaverse.workflow.common.response.WorkflowResponse;
import com.metaverse.workflow.common.util.RestControllerBase;
import com.metaverse.workflow.event.service.EventSessionRequest;
import com.metaverse.workflow.event.service.EventSessionService;
import com.metaverse.workflow.exceptions.DataException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/event-session")
@RequiredArgsConstructor
public class EventSessionController {

    private final EventSessionService service;

    @PostMapping
    public ResponseEntity<?> saveSession(@RequestBody EventSessionRequest request) {

        try {
            return ResponseEntity.ok(service.saveSession(request));
        } catch (DataException e) {
            return RestControllerBase.error(e);
        }
    }

    @PutMapping("/{sessionId}")
    public ResponseEntity<?> updateSession(@PathVariable Long sessionId,
                                                          @RequestBody EventSessionRequest request) {
        try {
            return ResponseEntity.ok(service.updateSession(sessionId, request));
        } catch (DataException e) {
            return RestControllerBase.error(e);
        }
    }

    @DeleteMapping("/{sessionId}")
    public ResponseEntity<?> deleteSession(@PathVariable Long sessionId) {
        try {
            return ResponseEntity.ok(service.deleteSession(sessionId));
        } catch (DataException e) {
            return RestControllerBase.error(e);
        }
    }

    @GetMapping("/{sessionId}")
    public ResponseEntity<?> getSessionById(@PathVariable Long sessionId) {
        try {
            return ResponseEntity.ok(service.getSessionById(sessionId));
        } catch (DataException e) {
            return RestControllerBase.error(e);
        }
    }

    @GetMapping("/event/{eventId}")
    public ResponseEntity<WorkflowResponse> getSessionsByEventId(@PathVariable Long eventId) {

        return ResponseEntity.ok(service.getSessionsByEventId(eventId));
    }

}