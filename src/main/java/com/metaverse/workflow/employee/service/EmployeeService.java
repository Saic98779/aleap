package com.metaverse.workflow.employee.service;

import com.metaverse.workflow.common.response.WorkflowResponse;
import com.metaverse.workflow.employee.repository.EmployeeRepository;
import com.metaverse.workflow.model.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository repository;

    public WorkflowResponse create(EmployeeRequest request) {

        Employee entity = EmployeeMapper.mapToEntity(request);
        Employee saved = repository.save(entity);

        return WorkflowResponse.builder()
                .status(200)
                .message("Employee Created Successfully")
                .data(EmployeeMapper.mapToResponse(saved))
                .build();
    }

    public WorkflowResponse update(Long id, EmployeeRequest request) {

        Employee entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        EmployeeMapper.updateEntity(entity,request);
        Employee updated = repository.save(entity);

        return WorkflowResponse.builder()
                .status(200)
                .message("Employee Updated Successfully")
                .data(EmployeeMapper.mapToResponse(updated))
                .build();
    }

    public WorkflowResponse getById(Long id) {

        Employee entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        return WorkflowResponse.builder()
                .status(200)
                .message("Success")
                .data(EmployeeMapper.mapToResponse(entity))
                .build();
    }

    public WorkflowResponse getAll() {

        List<EmployeeResponse> list = repository.findAll()
                .stream()
                .map(EmployeeMapper::mapToResponse)
                .toList();

        return WorkflowResponse.builder()
                .status(200)
                .message("Success")
                .data(list)
                .build();
    }

    public WorkflowResponse delete(Long id) {

        repository.deleteById(id);

        return WorkflowResponse.builder()
                .status(200)
                .message("Employee Deleted Successfully")
                .data(null)
                .build();
    }
}