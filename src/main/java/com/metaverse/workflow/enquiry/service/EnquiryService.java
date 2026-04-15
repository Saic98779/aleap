package com.metaverse.workflow.enquiry.service;

import com.metaverse.workflow.common.enums.Stage;
import com.metaverse.workflow.common.response.WorkflowResponse;
import com.metaverse.workflow.enquiry.repository.EnquiryRepository;
import com.metaverse.workflow.model.Enquiry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EnquiryService {

    private final EnquiryRepository repository;

    public WorkflowResponse create(EnquiryRequest request) {

        Enquiry entity = EnquiryMapper.mapToEntity(request);
        Enquiry saved = repository.save(entity);

        return WorkflowResponse.builder()
                .status(200)
                .message("Enquiry Created Successfully")
                .data(EnquiryMapper.mapToResponse(saved))
                .build();
    }

    public WorkflowResponse update(Long id, EnquiryRequest request) {

        Enquiry entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Enquiry not found"));

        entity.setWalkInName(request.getWalkInName());
        entity.setWhatsappNumber(request.getWhatsappNumber());
        entity.setEmail(request.getEmail());
        entity.setStartupOrCompanyName(request.getStartupOrCompanyName());
        entity.setInnovationDescription(request.getInnovationDescription());
        entity.setSectors(request.getSectors());
        if (request.getStage() != null) {
            entity.setStage(Stage.valueOf(request.getStage()));
        }
        entity.setIncubationSupportRequired(request.getIncubationSupportRequired());
        entity.setIncubationSupportDescription(request.getIncubationSupportDescription());
        entity.setSupportServicesRequired(request.getSupportServicesRequired());
        entity.setApplicantSignature(request.getApplicantSignature());
        entity.setFormReceivedDate(request.getFormReceivedDate());
        entity.setOfficialName(request.getOfficialName());
        entity.setDiscussionSummary(request.getDiscussionSummary());
        entity.setEnquiryFromFilePath(request.getEnquiryFromFilePath());
        entity.setState(request.getState());
        entity.setDistrict(request.getDistrict());
        entity.setMandal(request.getMandal());
        entity.setStreetOrBlock(request.getStreetOrBlock());
        entity.setHouseNoOrDoorNo(request.getHouseNoOrDoorNo());
        entity.setPinCode(request.getPinCode());

        Enquiry updated = repository.save(entity);

        return WorkflowResponse.builder()
                .status(200)
                .message("Enquiry Updated Successfully")
                .data(EnquiryMapper.mapToResponse(updated))
                .build();
    }

    public WorkflowResponse getById(Long id) {

        Enquiry entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Enquiry not found"));

        return WorkflowResponse.builder()
                .status(200)
                .message("Success")
                .data(EnquiryMapper.mapToResponse(entity))
                .build();
    }

    public WorkflowResponse getAll() {

        List<EnquiryResponse> list = repository.findAll()
                .stream()
                .map(EnquiryMapper::mapToResponse)
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
                .message("Enquiry Deleted Successfully")
                .data(null)
                .build();
    }
}
