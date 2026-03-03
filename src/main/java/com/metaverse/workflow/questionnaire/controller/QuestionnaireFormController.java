package com.metaverse.workflow.questionnaire.controller;


import com.metaverse.workflow.common.util.RestControllerBase;
import com.metaverse.workflow.exceptions.DataException;
import com.metaverse.workflow.questionnaire.service.QuestionnaireFormRequest;
import com.metaverse.workflow.questionnaire.service.QuestionnaireFormService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/questionnaire")
@RequiredArgsConstructor
public class QuestionnaireFormController {

    private final QuestionnaireFormService service;

    @PostMapping("/save")
    public ResponseEntity<?> create(@RequestBody QuestionnaireFormRequest request) {
        System.out.println("jdfn jvj");
        return  ResponseEntity.ok(service.create(request));
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody QuestionnaireFormRequest request) {
        try {
            return  ResponseEntity.ok(service.update(id, request));
        } catch (DataException e) {
            return RestControllerBase.error(e);
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try {
            return  ResponseEntity.ok(service.getById(id));
        } catch (DataException e) {
            return RestControllerBase.error(e);
        }
    }


    @GetMapping
    public ResponseEntity<?> getAll() {
        return  ResponseEntity.ok( service.getAll());
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
