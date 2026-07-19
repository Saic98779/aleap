package com.metaverse.workflow.membership.service;

import com.metaverse.workflow.common.response.WorkflowResponse;
import com.metaverse.workflow.login.repository.LoginRepository;
import com.metaverse.workflow.membership.repository.MembershipRepository;
import com.metaverse.workflow.model.MembershipApplication;
import com.metaverse.workflow.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MembershipService {

    private final MembershipRepository repository;
    private final LoginRepository loginRepository;

    public WorkflowResponse save(MembershipRequest request) {

        User user = loginRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        MembershipApplication application =
                MembershipApplicationMapper.toEntity(request, user);

        MembershipApplication saved = repository.save(application);

        return WorkflowResponse.builder()
                .status(200)
                .message("Membership application created successfully")
                .data(MembershipApplicationMapper.mapToResponse(saved))
                .build();
    }

    public WorkflowResponse update(Long id, MembershipRequest request) {

        MembershipApplication application = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Application not found"));

        MembershipApplicationMapper.updateEntity(application, request);

        MembershipApplication updated = repository.save(application);

        return WorkflowResponse.builder()
                .status(200)
                .message("Membership application updated successfully")
                .data(MembershipApplicationMapper.mapToResponse(updated))
                .build();
    }

    public WorkflowResponse getById(Long id) {

        MembershipApplication application = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Membership application not found"));

        return WorkflowResponse.builder()
                .status(200)
                .message("Success")
                .data(MembershipApplicationMapper.mapToResponse(application))
                .build();
    }

    public WorkflowResponse getAll() {

        List<MembershipApplicationResponse> response = repository.findAll()
                .stream()
                .map(MembershipApplicationMapper::mapToResponse)
                .collect(Collectors.toList());

        return WorkflowResponse.builder()
                .status(200)
                .message("Success")
                .data(response)
                .build();
    }

    public WorkflowResponse getByUserId(String userId) {

        List<MembershipApplicationResponse> response =
                repository.findByCreatedBy_UserId(userId)
                        .stream()
                        .map(MembershipApplicationMapper::mapToResponse)
                        .collect(Collectors.toList());

        return WorkflowResponse.builder()
                .status(200)
                .message("Success")
                .data(response)
                .build();
    }

    public WorkflowResponse delete(Long id) {

        repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Membership application not found"));

        repository.deleteById(id);

        return WorkflowResponse.builder()
                .status(200)
                .message("Deleted successfully")
                .build();
    }
}