package com.metaverse.workflow.project.service;


import com.metaverse.workflow.common.response.WorkflowResponse;
import com.metaverse.workflow.exceptions.DataException;
import com.metaverse.workflow.model.ProjectDetails;
import com.metaverse.workflow.project.repository.ProjectDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectDetailsService {

    private final ProjectDetailsRepository repository;

    public WorkflowResponse create(ProjectDetailsRequest request) {

        ProjectDetails entity = ProjectDetailsMapper.mapToEntity(request);
        ProjectDetails saved = repository.save(entity);

        return WorkflowResponse.builder()
                .status(200)
                .message("Project Created Successfully")
                .data(ProjectDetailsMapper.mapToResponse(saved))
                .build();
    }

    public WorkflowResponse update(Long id, ProjectDetailsRequest request) throws DataException {

        ProjectDetails existing = repository.findById(id)
                .orElseThrow(() -> new DataException(
                        "Project not found with id " + id,
                        "NOT_FOUND",
                        404
                ));

        ProjectDetails updated = ProjectDetailsMapper.mapToEntity(request);
        updated.setId(existing.getId());

        ProjectDetails saved = repository.save(updated);

        return WorkflowResponse.builder()
                .status(200)
                .message("Project Updated Successfully")
                .data(ProjectDetailsMapper.mapToResponse(saved))
                .build();
    }

    public WorkflowResponse getById(Long id) throws DataException {

        ProjectDetails entity = repository.findById(id)
                .orElseThrow(() -> new DataException(
                        "Project not found with id " + id,
                        "NOT_FOUND",
                        404
                ));

        return WorkflowResponse.builder()
                .status(200)
                .message("Success")
                .data(ProjectDetailsMapper.mapToResponse(entity))
                .build();
    }

    public WorkflowResponse getAll() {

        List<ProjectDetailsResponse> list = repository.findAll()
                .stream()
                .map(ProjectDetailsMapper::mapToResponse)
                .collect(Collectors.toList());

        return WorkflowResponse.builder()
                .status(200)
                .message("Success")
                .data(list)
                .build();
    }
    public WorkflowResponse getAllProjectDropdown() {

        List<ProjectDetailsResponseDropdown> list = repository.findAll()
                .stream()
                .map(ProjectDetailsMapper::mapToResponseDropdown)
                .collect(Collectors.toList());

        return WorkflowResponse.builder()
                .status(200)
                .message("Success")
                .data(list)
                .build();
    }

    public WorkflowResponse delete(Long id) throws DataException {

        if (!repository.existsById(id)) {
            throw new DataException(
                    "Project not found with id " + id,
                    "NOT_FOUND",
                    404
            );
        }

        repository.deleteById(id);

        return WorkflowResponse.builder()
                .status(200)
                .message("Project Deleted Successfully")
                .build();
    }
}