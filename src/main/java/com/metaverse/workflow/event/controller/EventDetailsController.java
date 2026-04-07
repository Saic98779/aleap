package com.metaverse.workflow.event.controller;


import com.metaverse.workflow.common.util.RestControllerBase;
import com.metaverse.workflow.event.service.EventDetailsDTO;
import com.metaverse.workflow.event.service.EventDetailsService;
import com.metaverse.workflow.exceptions.DataException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventDetailsController {

    private final EventDetailsService service;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody EventDetailsDTO request) {
        return ResponseEntity.ok(service.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,
                                    @RequestBody EventDetailsDTO request) {
        try {
            return ResponseEntity.ok(service.update(id, request));
        } catch (DataException e) {
            return RestControllerBase.error(e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(service.getById(id));
        } catch (DataException e) {
            return RestControllerBase.error(e);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(service.getAll());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(service.delete(id));
        } catch (DataException e) {
            return RestControllerBase.error(e);
        }
    }
}
