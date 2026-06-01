package com.metaverse.workflow.membership.controller;


import com.metaverse.workflow.common.response.WorkflowResponse;
import com.metaverse.workflow.membership.service.MembershipRequest;

import com.metaverse.workflow.membership.service.MembershipService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/membership")
@RequiredArgsConstructor
public class MembershipController {

    private final MembershipService service;

    @PostMapping
    public ResponseEntity<WorkflowResponse> save(@RequestBody MembershipRequest request) {
        return ResponseEntity.ok(service.save(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<WorkflowResponse> update(
            @PathVariable Long id,
            @RequestBody MembershipRequest request) {

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