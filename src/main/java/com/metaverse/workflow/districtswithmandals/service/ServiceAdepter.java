package com.metaverse.workflow.districtswithmandals.service;


import com.metaverse.workflow.common.response.WorkflowResponse;
import com.metaverse.workflow.districtswithmandals.repository.DistrictRepository;
import com.metaverse.workflow.districtswithmandals.repository.MandalRepository;
import com.metaverse.workflow.districtswithmandals.repository.StateRepository;
import com.metaverse.workflow.model.District;
import com.metaverse.workflow.model.Mandal;
import com.metaverse.workflow.model.State;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class ServiceAdepter implements Service {

    private final StateRepository stateRepository;
    private final DistrictRepository districtRepository;
    private final MandalRepository mandalRepository;


    @Override
    public WorkflowResponse getAllDistricts() {
        List<District> districtList = districtRepository.findAll();
        List<DistrictResponse> responses = districtList.stream()
                .map(DistrictResponceMapper::map)
                .collect(Collectors.toList());

        return WorkflowResponse.builder()
                .message("Success")
                .status(200)
                .data(responses)
                .build();


    }


    @Override
    public WorkflowResponse getAllMandalOfDistrict(Integer districtId) {
        List<Mandal> mandalList = mandalRepository.findByDistrictId(districtId);
        List<MandalResponse> mandalResponse = mandalList.stream()
                .map(MandalResponceMapper::map)
                .collect(Collectors.toList());

        return WorkflowResponse.builder()
                .message("Mandals get successfully")
                .status(200)
                .data(mandalResponse)
                .build();
    }

    @Override
    public WorkflowResponse getAllMandalOfDistrictName(String districtName) {
        List<Mandal> mandalList = mandalRepository.findByDistrict_DistrictName(districtName);
        List<MandalResponse> mandalResponse = mandalList.stream()
                .map(MandalResponceMapper::map)
                .collect(Collectors.toList());

        return WorkflowResponse.builder()
                .message("Mandals get successfully")
                .status(200)
                .data(mandalResponse)
                .build();
    }


    @Override
    public WorkflowResponse getDistrictById(Integer districtId) {
        Optional<District> district = districtRepository.findById(districtId);
        DistrictResponse districtResponse = DistrictResponceMapper.map(district.get());

        return WorkflowResponse.builder()
                .message("District get successfully")
                .status(200)
                .data(districtResponse)
                .build();
    }

    @Override
    public List<District> getAllDistrictsEntity() {
        return districtRepository.findAll();
    }

    @Override
    public List<Mandal> getAllMandalsEntity() {
        return mandalRepository.findAll();
    }

    @Override
    public WorkflowResponse getAllStates() {

        List<State> states = stateRepository.findAll();
        List<StateResponse> responses = states.stream()
                .map(Mapper::mapStates)
                .toList();
        return WorkflowResponse.builder()
                .message("States fetched successfully")
                .status(200)
                .data(responses)
                .build();
    }

    @Override
    public WorkflowResponse getDistrictsByStateId(Integer stateId) {

        List<District> districtList = districtRepository.findByState_StateId(stateId);

        List<DistrictResponse> responses = districtList.stream()
                .map(Mapper::mapDistricts)
                .collect(Collectors.toList());

        return WorkflowResponse.builder()
                .message("Districts fetched successfully")
                .status(200)
                .data(responses)
                .build();
    }

    @Override
    public WorkflowResponse getMandalsByDistrictId(Integer id) {
        List<Mandal> mandalList = mandalRepository.findByDistrictId(id);
        List<MandalResponse> mandalResponse = mandalList.stream()
                .map(Mapper::mapMandals)
                .collect(Collectors.toList());

        return WorkflowResponse.builder()
                .message("Mandals get successfully")
                .status(200)
                .data(mandalResponse)
                .build();
    }
}
