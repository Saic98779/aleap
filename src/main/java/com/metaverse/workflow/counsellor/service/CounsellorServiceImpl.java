package com.metaverse.workflow.counsellor.service;

import com.metaverse.workflow.common.response.WorkflowResponse;
import com.metaverse.workflow.common.util.DateUtil;
import com.metaverse.workflow.counsellor.repository.CounsellorAllotmentRepository;
import com.metaverse.workflow.counsellor.repository.CounsellorRegistrationRepository;
import com.metaverse.workflow.districtswithmandals.repository.MandalRepositrory;
import com.metaverse.workflow.exceptions.DataException;
import com.metaverse.workflow.model.CounsellorAllotment;
import com.metaverse.workflow.model.CounsellorRegistration;
import com.metaverse.workflow.model.Mandal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CounsellorServiceImpl implements CounsellorService {
    @Autowired
    CounsellorRegistrationRepository counsellorRegistrationRepository;
    @Autowired
    CounsellorAllotmentRepository counsellorAllotmentRepository;
    @Autowired
    MandalRepositrory mandalRepositrory;

    @Override
    public WorkflowResponse saveCounsellor(CounsellorRegistrationRequest request) {

        CounsellorRegistration counsellor = CounsellorRequestMapper.map(request);
        Optional<Mandal> mandal = mandalRepositrory.findById(request.getMandalId());
        if (mandal.isEmpty()) {
            return WorkflowResponse.builder()
                    .status(400)
                    .message("Registration Mandal not found")
                    .build();
        }

        counsellor.setRegistrationMandal(mandal.get());
        CounsellorRegistration saved = counsellorRegistrationRepository.save(counsellor);
        return WorkflowResponse.builder()
                .status(200)
                .message("Counsellor Registered Successfully")
                .data(CounsellorResponseMapper.map(saved))
                .build();
    }

    @Override
    public WorkflowResponse getAllCounsellors() {
        List<CounsellorRegistrationResponse> list = counsellorRegistrationRepository.findAll()
                        .stream()
                        .map(CounsellorResponseMapper::map)
                        .toList();

        return WorkflowResponse.builder()
                .status(200)
                .message("success")
                .data(list)
                .build();
    }

    @Override
    public WorkflowResponse getCounsellorById(Long id) {

        Optional<CounsellorRegistration> optional = counsellorRegistrationRepository.findById(id);

        if (optional.isEmpty()) {
            return WorkflowResponse.builder()
                    .status(404)
                    .message("Counsellor Not Found")
                    .build();
        }

        return WorkflowResponse.builder()
                .status(200)
                .message("success")
                .data(CounsellorResponseMapper.map(optional.get()))
                .build();
    }

    @Override
    public WorkflowResponse updateCounsellor(Long id, CounsellorRegistrationRequest request) {

        Optional<CounsellorRegistration> optional = counsellorRegistrationRepository.findById(id);

        if (optional.isEmpty()) {return WorkflowResponse.builder()
                    .status(404)
                    .message("Counsellor Not Found")
                    .build();
        }

        CounsellorRegistration counsellor = optional.get();
        counsellor.setNameOfCounsellor(request.getNameOfCounsellor());
        counsellor.setEmailId(request.getEmailId());
        counsellor.setContactNo(request.getContactNo());

        CounsellorRegistration updated = counsellorRegistrationRepository.save(counsellor);

        return WorkflowResponse.builder()
                .status(200)
                .message("Updated Successfully")
                .data(CounsellorResponseMapper.map(updated))
                .build();
    }

    @Override
    public WorkflowResponse getAllAllotments() {

        List<CounsellorAllotment> allotments = counsellorAllotmentRepository.findAll();
        if (allotments.isEmpty()) {
            return WorkflowResponse.builder()
                    .status(200)
                    .message("No allotments found")
                    .data(List.of())
                    .build();
        }
        List<CounsellorAllotmentsResponse> responses = allotments.stream()
                .map(allotment -> CounsellorAllotmentsResponse.builder()
                        .counsellorAllotmentId(allotment.getCounsellorAllotmentId())
                        .mandalId(allotment.getAllotedMandal() != null ?
                                allotment.getAllotedMandal().getMandalId() : null)
                        .mandalName(allotment.getAllotedMandal() != null ?
                                allotment.getAllotedMandal().getMandalName() : null)
                        .startDate(allotment.getStartDate() != null ?
                                DateUtil.dateToString(allotment.getStartDate(), "dd-MM-yyyy") : null)
                        .endDate(allotment.getEndDate() != null ?
                                DateUtil.dateToString(allotment.getEndDate(), "dd-MM-yyyy") : null)
                        .dateOfAllotment(allotment.getDateOfAllotment() != null ?
                                DateUtil.dateToString(allotment.getDateOfAllotment(), "dd-MM-yyyy") : null)
                        .build())
                .toList();

        return WorkflowResponse.builder()
                .status(200)
                .message("All allotments fetched successfully")
                .data(responses)
                .build();
    }

    @Override
    public WorkflowResponse deleteCounsellor(Long id) {

        if (!counsellorRegistrationRepository.existsById(id)) {
            return WorkflowResponse.builder()
                    .status(404)
                    .message("Counsellor Not Found")
                    .build();
        }

        counsellorRegistrationRepository.deleteById(id);

        return WorkflowResponse.builder()
                .status(200)
                .message("Deleted Successfully")
                .build();
    }
    @Override
    public WorkflowResponse assignMandal(CounsellorAllotmentsRequest request) throws DataException {

        CounsellorRegistration counsellor = counsellorRegistrationRepository.findById(request.getCounsellorRegistrationId())
                .orElseThrow(() ->
                        new DataException("Counsellor Not Found", "NOT_FOUND"));
        Mandal mandal = mandalRepositrory.findById(request.getMandalId())
                .orElseThrow(() ->
                        new DataException("Mandal Not Found","NOT_FOUND"));

        CounsellorAllotment allotment = new CounsellorAllotment();
        allotment.setCounsellorRegistration(counsellor);
        allotment.setAllotedMandal(mandal);
        allotment.setStartDate(DateUtil.stringToDate(request.getStartDate(), "dd-MM-yyyy"));
        allotment.setEndDate(DateUtil.stringToDate(request.getEndDate(), "dd-MM-yyyy"));
        allotment.setDateOfAllotment(DateUtil.stringToDate(request.getDateOfAllotment(), "dd-MM-yyyy"));

        counsellor.getCounsellorAllotments().add(allotment);
        counsellorAllotmentRepository.save(allotment);

        return WorkflowResponse.builder()
                .status(200)
                .message("Mandal Assigned Successfully")
                .build();
    }

    @Override
    public WorkflowResponse getCounsellorByMandal(Integer mandalId) {

        Optional<Mandal> mandalOpt = mandalRepositrory.findById(mandalId);
        if (mandalOpt.isEmpty()) {
            return WorkflowResponse.builder()
                    .status(404)
                    .message("Mandal not found")
                    .build();
        }

        List<CounsellorAllotment> allotments = counsellorAllotmentRepository.findByAllotedMandal(mandalOpt.get());

        if (allotments.isEmpty()) {
            return WorkflowResponse.builder()
                    .status(404)
                    .message("No counsellors allocated for this mandal")
                    .build();
        }

        List<CounsellorRegistrationResponse> responses =
                allotments.stream()
                        .map(allotment ->
                                CounsellorResponseMapper
                                        .map(allotment.getCounsellorRegistration()))
                        .toList();

        return WorkflowResponse.builder()
                .status(200)
                .message("success")
                .data(responses)
                .build();
    }

}
