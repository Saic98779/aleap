package com.metaverse.workflow.employee.service;

import lombok.Data;

@Data
public class EmployeeRequest {

    private String name;
    private String educationalQualification;
    private String gender;
    private String category;
    private String designation;
    private String dateOfJoining;
    private String dateOfRelieving;
    private String phone;
    private String email;
    private String photo;
}
