package com.metaverse.workflow.executiondocumentation.service;

import com.metaverse.workflow.common.response.WorkflowResponse;
import com.metaverse.workflow.executiondocumentation.repository.ExecutionDocumentationRepository;
import com.metaverse.workflow.login.repository.LoginRepository;
import com.metaverse.workflow.model.ExecutionDocumentation;
import com.metaverse.workflow.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExecutionDocumentationService {

    private final ExecutionDocumentationRepository repository;
    private final LoginRepository loginRepository;

    public WorkflowResponse save(ExecutionDocumentationRequest request) {

        User user = loginRepository.findById(request.getUserId())
                .orElse(null);

        if (user == null) {
            return WorkflowResponse.builder()
                    .status(404)
                    .message("User not found")
                    .build();
        }

        ExecutionDocumentation documentation =
                ExecutionDocumentationMapper.toEntity(request, user);

        return WorkflowResponse.builder()
                .status(200)
                .message("Execution documentation created successfully")
                .data(repository.save(documentation))
                .build();
    }

    public WorkflowResponse update(Long id,
                                   ExecutionDocumentationRequest request) {

        ExecutionDocumentation documentation = repository.findById(id)
                .orElseThrow(()-> new RuntimeException("Execution documentation not found"));

        ExecutionDocumentationMapper.updateEntity(documentation, request);

        return WorkflowResponse.builder()
                .status(200)
                .message("Execution documentation updated successfully")
                .data(repository.save(documentation))
                .build();
    }

    public WorkflowResponse getById(Long id) {

        ExecutionDocumentation documentation = repository.findById(id)
                .orElseThrow(()-> new RuntimeException("Execution documentation not found"));
        return WorkflowResponse.builder()
                .status(200)
                .message("Success")
                .data(documentation)
                .build();
    }

    public WorkflowResponse getAll() {

        List<ExecutionDocumentation> list = repository.findAll();

        if (list.isEmpty()) {
            return WorkflowResponse.builder()
                    .status(404)
                    .message("No execution documentation found")
                    .build();
        }

        return WorkflowResponse.builder()
                .status(200)
                .message("Success")
                .data(list)
                .build();
    }

    public WorkflowResponse getByProgramId(Long programId) {

        ExecutionDocumentation documentation = repository.findByProgramId(programId).orElse(null);

        if (documentation == null) {
            return WorkflowResponse.builder()
                    .status(404)
                    .message("Execution documentation not found for program")
                    .build();
        }

        return WorkflowResponse.builder()
                .status(200)
                .message("Success")
                .data(documentation)
                .build();
    }

    public WorkflowResponse getByEventId(Long eventId) {

        ExecutionDocumentation documentation = repository.findByEventId(eventId).orElse(null);

        if (documentation == null) {
            return WorkflowResponse.builder()
                    .status(404)
                    .message("Execution documentation not found for event")
                    .build();
        }

        return WorkflowResponse.builder()
                .status(200)
                .message("Success")
                .data(documentation)
                .build();
    }

    public WorkflowResponse getByUserId(String userId) {

        List<ExecutionDocumentation> list = repository.findByCreatedBy_UserId(userId);

        if (list.isEmpty()) {
            return WorkflowResponse.builder()
                    .status(404)
                    .message("No execution documentation found for user")
                    .build();
        }

        return WorkflowResponse.builder()
                .status(200)
                .message("Success")
                .data(list)
                .build();
    }

    public WorkflowResponse delete(Long id) {

        ExecutionDocumentation documentation = repository.findById(id)
                .orElseThrow(()-> new RuntimeException("Execution documentation not found"));
        repository.delete(documentation);

        return WorkflowResponse.builder()
                .status(200)
                .message("Execution documentation deleted successfully")
                .build();
    }
}