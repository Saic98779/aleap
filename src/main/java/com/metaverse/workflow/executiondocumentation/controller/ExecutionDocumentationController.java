package com.metaverse.workflow.executiondocumentation.controller;

import com.metaverse.workflow.common.response.WorkflowResponse;
import com.metaverse.workflow.executiondocumentation.service.ExecutionDocumentationRequest;
import com.metaverse.workflow.executiondocumentation.service.ExecutionDocumentationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/execution-documentation")
@RequiredArgsConstructor
public class ExecutionDocumentationController {

    private final ExecutionDocumentationService service;

    @PostMapping
    public ResponseEntity<WorkflowResponse> save(
            @RequestBody ExecutionDocumentationRequest request) {

        return ResponseEntity.ok(service.save(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<WorkflowResponse> update(
            @PathVariable Long id,
            @RequestBody ExecutionDocumentationRequest request) {

        return ResponseEntity.ok(service.update(id, request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkflowResponse> getById(
            @PathVariable Long id) {

        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping
    public ResponseEntity<WorkflowResponse> getAll() {

        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/program/{programId}")
    public ResponseEntity<WorkflowResponse> getByProgramId(
            @PathVariable Long programId) {

        return ResponseEntity.ok(service.getByProgramId(programId));
    }

    @GetMapping("/event/{eventId}")
    public ResponseEntity<WorkflowResponse> getByEventId(
            @PathVariable Long eventId) {

        return ResponseEntity.ok(service.getByEventId(eventId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<WorkflowResponse> getByUserId(
            @PathVariable String userId) {

        return ResponseEntity.ok(service.getByUserId(userId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<WorkflowResponse> delete(
            @PathVariable Long id) {

        return ResponseEntity.ok(service.delete(id));
    }
}