package com.metaverse.workflow.districtswithmandals.controller;

import com.metaverse.workflow.common.response.WorkflowResponse;
import com.metaverse.workflow.districtswithmandals.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DistrictController {
    @Autowired
    private Service service;

    @GetMapping("/getAllDistricts")
    public WorkflowResponse getAllDistrict() {
        return service.getAllDistricts();
    }

    @GetMapping("/districtsById/{id}")
    public WorkflowResponse getAllDistrict(@PathVariable Integer id) {
        return service.getDistrictById(id);
    }

    @GetMapping("/getAllmandalsOfDistrictsById/{id}")
    public WorkflowResponse getAllMandalOfDistrict(@PathVariable Integer id) {
        return service.getAllMandalOfDistrict(id);
    }

    @GetMapping("/getAllmandalsOfDistrictsByName/{name}")
    public WorkflowResponse getAllMandalOfDistrict(@PathVariable String name) {
        return service.getAllMandalOfDistrictName(name);
    }

    @GetMapping("/states")
    public WorkflowResponse getStates() {
        return service.getAllStates();
    }

    @GetMapping("/districts/{stateId}")
    public WorkflowResponse getDistrictsByState(@PathVariable Integer stateId) {
        return service.getDistrictsByStateId(stateId);
    }

    @GetMapping("/mandals/{districtId}")
    public WorkflowResponse getMandalsByDistrict(@PathVariable Integer districtId) {
        return service.getAllMandalOfDistrict(districtId);
    }

}
