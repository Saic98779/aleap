package com.metaverse.workflow.project.controller;



import com.metaverse.workflow.common.util.RestControllerBase;
import com.metaverse.workflow.exceptions.DataException;
import com.metaverse.workflow.project.service.ProjectDetailsRequest;
import com.metaverse.workflow.project.service.ProjectDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/projects")
@RequiredArgsConstructor
public class ProjectDetailsController {

    private final ProjectDetailsService service;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ProjectDetailsRequest request) {
        return ResponseEntity.ok(service.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,
                                    @RequestBody ProjectDetailsRequest request) {
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

    @GetMapping("/dropdown")
    public ResponseEntity<?> getAllProjects() {
        return ResponseEntity.ok(service.getAllProjectDropdown());
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
