package com.metaverse.workflow.counsellor.controller;

import com.metaverse.workflow.common.response.WorkflowResponse;
import com.metaverse.workflow.common.util.RestControllerBase;
import com.metaverse.workflow.counsellor.service.CounsellorAllotmentsRequest;
import com.metaverse.workflow.counsellor.service.CounsellorRegistrationRequest;
import com.metaverse.workflow.counsellor.service.CounsellorService;
import com.metaverse.workflow.exceptions.DataException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/counsellor")
@AllArgsConstructor
@Slf4j
public class CounsellorController {

    private final CounsellorService counsellorService;

    @PostMapping("/save")
    public ResponseEntity<WorkflowResponse> saveCounsellor(
            @RequestBody CounsellorRegistrationRequest request) {

        log.info("Saving counsellor");
        return ResponseEntity.ok(
                counsellorService.saveCounsellor(request));
    }


    @GetMapping("/all")
    public ResponseEntity<WorkflowResponse> getAllCounsellors() {
        return ResponseEntity.ok(counsellorService.getAllCounsellors());
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkflowResponse> getCounsellorById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                counsellorService.getCounsellorById(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<WorkflowResponse> updateCounsellor(@PathVariable Long id,
                                                             @RequestBody CounsellorRegistrationRequest request) {
        return ResponseEntity.ok(counsellorService.updateCounsellor(id, request));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<WorkflowResponse> deleteCounsellor(@PathVariable Long id) {
        return ResponseEntity.ok(counsellorService.deleteCounsellor(id));
    }

    @PostMapping("/assign-mandal")
    public ResponseEntity<?> assignMandal(@RequestBody CounsellorAllotmentsRequest request) {
        try {
            log.info("dcj  cm  cw ");
            return ResponseEntity.ok(counsellorService.assignMandal(request));
        } catch (DataException e) {
            return RestControllerBase.error(e);
        }
    }

    @GetMapping("/allotments")
    public ResponseEntity<WorkflowResponse> getAllAllotments() {
        return ResponseEntity.ok(counsellorService.getAllAllotments());
    }

    @GetMapping("/by-mandal/{mandalId}")
    public ResponseEntity<WorkflowResponse> getCounsellorByMandal(@PathVariable Integer mandalId) {
        return ResponseEntity.ok(counsellorService.getCounsellorByMandal(mandalId));
    }
}