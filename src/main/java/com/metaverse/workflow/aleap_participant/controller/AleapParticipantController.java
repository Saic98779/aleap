package com.metaverse.workflow.aleap_participant.controller;

import com.metaverse.workflow.aleap_participant.service.AleapParticipantRequest;
import com.metaverse.workflow.aleap_participant.service.AleapParticipantService;
import com.metaverse.workflow.common.response.WorkflowResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/aleap-participant")
@RequiredArgsConstructor
public class AleapParticipantController {

    private final AleapParticipantService service;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody AleapParticipantRequest request) {
        return ResponseEntity.ok(service.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,
                                    @RequestBody AleapParticipantRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return ResponseEntity.ok(service.delete(id));
    }

    @GetMapping("/mobile/{mobileNumber}")
    public ResponseEntity<?> findByMobileNumber(@PathVariable Long mobileNumber) {
        return ResponseEntity.ok(service.findByMobileNumber(mobileNumber));
    }

    @PostMapping(path = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public WorkflowResponse uploadExcel(@RequestParam("file") MultipartFile file) {
        if (!file.getOriginalFilename().endsWith(".xlsx")) {
            return WorkflowResponse.builder()
                    .message("Only Excel (.xlsx) files allowed")
                    .status(400)
                    .build();
        }
        return service.uploadExcel(file);
    }
}
