package com.metaverse.workflow.enquiry.controller;

import com.metaverse.workflow.enquiry.service.EnquiryRequest;
import com.metaverse.workflow.enquiry.service.EnquiryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/enquiry")
@RequiredArgsConstructor
public class EnquiryController {

    private final EnquiryService service;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody EnquiryRequest request) {
        return ResponseEntity.ok(service.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,
                                    @RequestBody EnquiryRequest request) {
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
}