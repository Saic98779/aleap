package com.metaverse.workflow.districtswithmandals.service;

import com.metaverse.workflow.common.response.WorkflowResponse;
import com.metaverse.workflow.model.District;
import com.metaverse.workflow.model.Mandal;

import java.util.List;

public interface Service {

    WorkflowResponse getAllDistricts();
    WorkflowResponse getAllMandalOfDistrict(Integer id);
    WorkflowResponse getDistrictById(Integer id);
    List<District> getAllDistrictsEntity();
    List<Mandal> getAllMandalsEntity();
    WorkflowResponse getAllMandalOfDistrictName(String name);
    WorkflowResponse getAllStates();
    WorkflowResponse getDistrictsByStateId(Integer stateId);
    WorkflowResponse getMandalsByDistrictId(Integer id);
}
