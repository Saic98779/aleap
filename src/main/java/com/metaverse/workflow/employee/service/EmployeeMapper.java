package com.metaverse.workflow.employee.service;

import com.metaverse.workflow.common.util.DateUtil;
import com.metaverse.workflow.model.Employee;

public class EmployeeMapper {

    public static Employee mapToEntity(EmployeeRequest request) {
        return Employee.builder()
                .name(request.getName())
                .educationalQualification(request.getEducationalQualification())
                .gender(request.getGender())
                .category(request.getCategory())
                .designation(request.getDesignation())
                .dateOfJoining(DateUtil.stringToDates(request.getDateOfJoining()))
                .dateOfRelieving(DateUtil.stringToDates(request.getDateOfRelieving()))
                .phone(request.getPhone())
                .email(request.getEmail())
                .photo(request.getPhoto())
                .build();
    }

    public static EmployeeResponse mapToResponse(Employee employee) {
        return EmployeeResponse.builder()
                .id(employee.getId())
                .name(employee.getName())
                .educationalQualification(employee.getEducationalQualification())
                .gender(employee.getGender())
                .category(employee.getCategory())
                .designation(employee.getDesignation())
                .dateOfJoining(employee.getDateOfJoining().toString())
                .dateOfRelieving(employee.getDateOfRelieving().toString())
                .phone(employee.getPhone())
                .email(employee.getEmail())
                .photo(employee.getPhoto())
                .build();
    }

    public static void updateEntity(Employee employee, EmployeeRequest request) {
        employee.setName(request.getName());
        employee.setEducationalQualification(request.getEducationalQualification());
        employee.setGender(request.getGender());
        employee.setCategory(request.getCategory());
        employee.setDesignation(request.getDesignation());
        employee.setDateOfJoining(DateUtil.stringToDates(request.getDateOfJoining()));
        employee.setDateOfRelieving(DateUtil.stringToDates(request.getDateOfRelieving()));
        employee.setPhone(request.getPhone());
        employee.setEmail(request.getEmail());
        employee.setPhoto(request.getPhoto());
    }
}
