package com.metaverse.workflow.enquiry.service;

import com.metaverse.workflow.common.enums.Stage;
import com.metaverse.workflow.model.Enquiry;

public class EnquiryMapper {

    public static Enquiry mapToEntity(EnquiryRequest request) {

        return Enquiry.builder()
                .walkInName(request.getWalkInName())
                .whatsappNumber(request.getWhatsappNumber())
                .address(request.getAddress())
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
                .build();
    }

    public static EnquiryResponse mapToResponse(Enquiry entity) {

        return EnquiryResponse.builder()
                .id(entity.getId())
                .walkInName(entity.getWalkInName())
                .whatsappNumber(entity.getWhatsappNumber())
                .address(entity.getAddress())
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
                .build();
    }
}
