package com.metaverse.workflow.membership.service;



import com.metaverse.workflow.common.response.WorkflowResponse;
import com.metaverse.workflow.login.repository.LoginRepository;
import com.metaverse.workflow.model.MembershipApplication;
import com.metaverse.workflow.model.User;
import com.metaverse.workflow.membership.repository.MembershipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MembershipService  {

    private final MembershipRepository repository;
    private final LoginRepository loginRepository;

   
    public WorkflowResponse save(MembershipRequest request) {

        User user = loginRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        MembershipApplication application =
                MembershipApplicationMapper.toEntity(request, user);

        return WorkflowResponse.builder()
                .status(200)
                .message("Membership application created successfully")
                .data(repository.save(application))
                .build();
    }

     
    public WorkflowResponse update(Long id, MembershipRequest request) {

        MembershipApplication application = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Application not found"));

        MembershipApplicationMapper.updateEntity(application, request);

        return WorkflowResponse.builder()
                .status(200)
                .message("Membership application updated successfully")
                .data(repository.save(application))
                .build();
    }

     
    public WorkflowResponse getById(Long id) {
        MembershipApplication application = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Membership application not found"));
        return WorkflowResponse.builder()
                .status(200)
                .message("Success")
                .data(application)
                .build();
    }

     
    public WorkflowResponse getAll() {

        List<MembershipApplication> list = repository.findAll();

        return WorkflowResponse.builder()
                .status(200)
                .message("Success")
                .data(list)
                .build();
    }

     
    public WorkflowResponse getByUserId(String userId) {

        return WorkflowResponse.builder()
                .status(200)
                .message("Success")
                .data(repository.findByCreatedBy_UserId(userId))
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
