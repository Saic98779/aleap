package com.metaverse.workflow.enquiry.service;

import com.metaverse.workflow.common.enums.Stage;
import com.metaverse.workflow.model.Enquiry;

public class EnquiryMapper {

    public static Enquiry mapToEntity(EnquiryRequest request) {

        return Enquiry.builder()
                .walkInName(request.getWalkInName())
                .whatsappNumber(request.getWhatsappNumber())
                .email(request.getEmail())
                .startupOrCompanyName(request.getStartupOrCompanyName())
                .innovationDescription(request.getInnovationDescription())
                .sectors(request.getSectors())
                .stage(request.getStage() != null ? Stage.valueOf(request.getStage()) : null)
                .incubationSupportRequired(request.getIncubationSupportRequired())
                .incubationSupportDescription(request.getIncubationSupportDescription())
                .supportServicesRequired(request.getSupportServicesRequired())
                .applicantSignature(request.getApplicantSignature())
                .formReceivedDate(request.getFormReceivedDate())
                .officialName(request.getOfficialName())
                .discussionSummary(request.getDiscussionSummary())
                .enquiryFromFilePath(request.getEnquiryFromFilePath())
                .state(request.getState())
                .district(request.getDistrict())
                .mandal(request.getMandal())
                .streetOrBlock(request.getStreetOrBlock())
                .houseNoOrDoorNo(request.getHouseNoOrDoorNo())
                .pinCode(request.getPinCode())
                .build();
    }

    public static EnquiryResponse mapToResponse(Enquiry entity) {

        return EnquiryResponse.builder()
                .id(entity.getId())
                .walkInName(entity.getWalkInName())
                .whatsappNumber(entity.getWhatsappNumber())
                .email(entity.getEmail())
                .startupOrCompanyName(entity.getStartupOrCompanyName())
                .innovationDescription(entity.getInnovationDescription())
                .sectors(entity.getSectors())
                .stage(entity.getStage() != null ? entity.getStage().name() : null)
                .incubationSupportRequired(entity.getIncubationSupportRequired())
                .incubationSupportDescription(entity.getIncubationSupportDescription())
                .supportServicesRequired(entity.getSupportServicesRequired())
                .applicantSignature(entity.getApplicantSignature())
                .formReceivedDate(entity.getFormReceivedDate())
                .officialName(entity.getOfficialName())
                .discussionSummary(entity.getDiscussionSummary())
                .enquiryFromFilePath(entity.getEnquiryFromFilePath())
                .state(entity.getState())
                .district(entity.getDistrict())
                .mandal(entity.getMandal())
                .streetOrBlock(entity.getStreetOrBlock())
                .houseNoOrDoorNo(entity.getHouseNoOrDoorNo())
                .pinCode(entity.getPinCode())
                .build();
    }
}
